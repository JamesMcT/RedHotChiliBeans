package com.team6.project.services.rest.performance.test;

import static com.jayway.restassured.RestAssured.config;
import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.config.LogConfig;
import com.jayway.restassured.filter.session.SessionFilter;
import com.jayway.restassured.http.ContentType;
import com.team6.project.services.QueryServiceLocal;
import com.team6.project.services.rest.test.NetworkManagementRestServiceTest;

@RunWith(Arquillian.class)
public class PerformanceRestTestImsi {

    public final static String ARCHIVE_NAME = "test";
    public final static String WEBAPP_SRC = "src/main/webapp/protected";
    public final static double MAX_QUERY_TIME = 10.0;

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive a = ShrinkWrap
                .create(WebArchive.class, ARCHIVE_NAME + ".war")
                .addPackages(true, "com.team6.project.services",
                        "com.team6.project.services.rest",
                        "com.team6.project.entities",
                        "com.team6.project.readers", "com.team6.project.dao",
                        "com.team6.project.dao.jpa",
                        "com.team6.project.validators")
                .addAsResource("test-persistence-performance.xml",
                        "META-INF/persistence.xml")
                .addAsResource("log4j.xml", "log4j.xml")
                .merge(ShrinkWrap.create(GenericArchive.class)
                        .as(ExplodedImporter.class).importDirectory(WEBAPP_SRC)
                        .as(GenericArchive.class), "protected",
                        Filters.includeAll())
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"))
                .addAsWebInfResource("test-jboss-performance.xml",
                        "jboss-web.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE,
                        ArchivePaths.create("beans.xml"));
        File[] files = Maven.resolver()
                .resolve("com.jayway.restassured:rest-assured:2.4.0")
                .withTransitivity().as(File.class);
        a.addAsLibraries(files);

        files = Maven.resolver().resolve("org.apache.poi:poi:3.11")
                .withTransitivity().as(File.class);
        a.addAsLibraries(files);

        files = Maven.resolver().resolve("org.apache.commons:commons-io:1.3.2")
                .withTransitivity().as(File.class);
        a.addAsLibraries(files);

        files = Maven.resolver()
                .resolve("commons-logging:commons-logging:1.1.3")
                .withTransitivity().as(File.class);
        a.addAsLibraries(files);
        return a;
    }

    @Inject
    private QueryServiceLocal queryService;
    private FormAuthConfig fac;
    private SessionFilter sessionFilter;
    private String startDate = "2012-01-01 00:00:00";// "2013-02-01 21:01:00";
    private String endDate = "2015-01-01 00:00:00";// "2013-03-21 21:01:00";
    private Logger performanceLogger = org.apache.log4j.Logger
            .getLogger(PerformanceRestTest.class);

    @Before
    public void setUp() throws InterruptedException {
        RestAssured.config = config()
                .logConfig(new LogConfig(System.out, true));
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.basePath = ARCHIVE_NAME;
        RestAssured.port = 18080;
        fac = getformAuthConfig();
        sessionFilter = new SessionFilter();
        given().filter(sessionFilter).when().get("protected/index.jsp").then()
                .statusCode(200);
        given().auth().form("admin", "admin", fac).filter(sessionFilter).when()
                .get("protected/index.jsp");
    }

    @Test
    public void getUniqueEventCauseByImsi_1() throws InterruptedException {
        ArrayList<BigInteger> allImsi = (ArrayList<BigInteger>) queryService
                .getAllImsi();
        int j = 0;
        while (j < allImsi.size()) {
        	for (int i = 0; i < 10; i++) {
        		if (i+j >= allImsi.size()) break;
        		
                long beginTime = System.currentTimeMillis();
                given().filter(sessionFilter)
                        .expect()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .when()
                        .get("/protected/rest/customerservice/uniqueec/"
                                + allImsi.get(j+i));
                long endTime = System.currentTimeMillis();
                double timeTaken = (endTime - beginTime) / 1000.0;
                assertTrue(timeTaken < MAX_QUERY_TIME);     
                
                performanceLogger
                .warn(String
                        .format("SupportEngineer-getAllIMSIinRangeTime : loading in (%s seconds)",
                                new DecimalFormat("0.000").format(timeTaken)));
            }
            j = j + 2000;
            Thread.sleep(50);
        }
    }

  
    @Test
	public void testFailureCount() throws InterruptedException {
    	long startDateL = NetworkManagementRestServiceTest.dateConvert(startDate);
        long endDateL = NetworkManagementRestServiceTest.dateConvert(endDate);
    	
    	ArrayList<BigInteger> allImsi = (ArrayList<BigInteger>) queryService
                .getAllImsi();
    	
        int j = 0;
        while (j < allImsi.size()) {
        	for (int i = 0; i < 10; i++) {
        		if (i+j >= allImsi.size()) break;
		
        		long beginTime = System.currentTimeMillis();
				given().filter(sessionFilter)
						.expect()
						.statusCode(200)
						.when()
						.get("protected/rest/customerservice/countImsi?imsi="
								+ allImsi.get(j+i)
								+ "&startDate="
								+ startDateL
								+ "1275239700000&endDate="
								+ endDateL);
				long endTime = System.currentTimeMillis();
                double timeTaken = (endTime - beginTime) / 1000.0;
                assertTrue(timeTaken < MAX_QUERY_TIME);  
               
                performanceLogger
                .warn(String
                        .format("SupportEngineer-getAllIMSIinRangeTime : loading in (%s seconds)",
                                new DecimalFormat("0.000").format(timeTaken)));
        	}	
        	j = j + 2000;
            Thread.sleep(50);
        }
	}
    
    public FormAuthConfig getformAuthConfig() {
        return new FormAuthConfig("protected/j_security_check", "j_username",
                                  "j_password");

    }

}
