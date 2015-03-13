package com.team6.project.services.rest.test;

import static com.jayway.restassured.RestAssured.config;
import static com.jayway.restassured.RestAssured.given;

import java.io.File;
import java.text.DecimalFormat;

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

@RunWith(Arquillian.class)
public class PerformanceRestTest {

    public final static String ARCHIVE_NAME = "test";
    public final static String WEBAPP_SRC = "src/main/webapp/protected";
    public final static long MAX_QUERY_TIME = 2000;


    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive a = ShrinkWrap
                .create(WebArchive.class, ARCHIVE_NAME + ".war")
                .addPackages(true, "com.team6.project.services",
                             "com.team6.project.services.rest",
                             "com.team6.project.entities",
                             "com.team6.project.readers",
                             "com.team6.project.dao",
                             "com.team6.project.dao.jpa",
                             "com.team6.project.validators")
                .addAsResource("test-persistence-performance.xml",
                               "META-INF/persistence.xml")
                .addAsResource("log4j.xml",
                               "log4j.xml")
                .merge(ShrinkWrap.create(GenericArchive.class)
                               .as(ExplodedImporter.class)
                               .importDirectory(WEBAPP_SRC)
                               .as(GenericArchive.class), "protected",
                       Filters.includeAll())
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"))
                .addAsWebInfResource("test-jboss-performance.xml", "jboss-web.xml")
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

        files = Maven.resolver().resolve("org.glassfish:javax.json:1.0")
                .withTransitivity().as(File.class);
        a.addAsLibraries(files);

        return a;
    }
    
    private FormAuthConfig fac;
    private SessionFilter sessionFilter;
    private Logger performanceLogger = org.apache.log4j.Logger.getLogger(PerformanceRestTest.class);

    @Before
    public void setUp() throws InterruptedException {
        RestAssured.config = config()
                .logConfig(new LogConfig(System.out, true));
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.basePath = ARCHIVE_NAME;
        RestAssured.port = 8080;
        fac = getformAuthConfig();
        sessionFilter = new SessionFilter();
    }
    
    @Test
    public void testGetDistinctEventByTac_103600() {
        given().filter(sessionFilter).when()
                .get("/protected/rest/networkmanagement/eventidcausecode/103600").then()
                .statusCode(200);
        long beginTime = System.currentTimeMillis();
        given().auth().form("admin", "admin", fac).filter(sessionFilter)
                .expect().statusCode(200).contentType(ContentType.JSON).when()
                .get("/protected/rest/networkmanagement/eventidcausecode/103600");
        long endTime = System.currentTimeMillis();
        double timeTaken = (endTime-beginTime)/1000.0;
        performanceLogger.warn(String
                                      .format("NetworkManagment-GetDistinctEventByTac (103600): loading in (%s seconds)",
                                              new DecimalFormat("0.00").format(timeTaken)));
    }

    @Test
    public void testGetDistinctEventByTac_100100() {
        given().filter(sessionFilter).when()
                .get("/protected/rest/networkmanagement/eventidcausecode/100100").then()
                .statusCode(200);
        long beginTime = System.currentTimeMillis();
        given().auth().form("admin", "admin", fac).filter(sessionFilter)
                .expect().statusCode(200).contentType(ContentType.JSON).when()
                .get("/protected/rest/networkmanagement/eventidcausecode/100100");
        long endTime = System.currentTimeMillis();
        double timeTaken = (endTime-beginTime)/1000.0;
        performanceLogger.warn(String
                                      .format("NetworkManagment-GetDistinctEventByTac (100100): loading in (%s seconds)",
                                              new DecimalFormat("0.00").format(timeTaken)));
    }
    
    @Test
    public void testGetDistinctEventByTac_33002353() {
        given().filter(sessionFilter).when()
                .get("/protected/rest/networkmanagement/eventidcausecode/33002353").then()
                .statusCode(200);
        long beginTime = System.currentTimeMillis();
        given().auth().form("admin", "admin", fac).filter(sessionFilter)
                .expect().statusCode(200).contentType(ContentType.JSON).when()
                .get("/protected/rest/networkmanagement/eventidcausecode/33002353");
        long endTime = System.currentTimeMillis();
        double timeTaken = (endTime-beginTime)/1000.0;
        performanceLogger.warn(String
                                      .format("NetworkManagment-GetDistinctEventByTac (33002353): loading in (%s seconds)",
                                              new DecimalFormat("0.00").format(timeTaken)));
    }
    @Test
    public void testGetDistinctEventByTac_33002235() {
        given().filter(sessionFilter).when()
                .get("/protected/rest/networkmanagement/eventidcausecode/33002235").then()
                .statusCode(200);
        long beginTime = System.currentTimeMillis();
        given().auth().form("admin", "admin", fac).filter(sessionFilter)
                .expect().statusCode(200).contentType(ContentType.JSON).when()
                .get("/protected/rest/networkmanagement/eventidcausecode/33002235");
        long endTime = System.currentTimeMillis();
        double timeTaken = (endTime-beginTime)/1000.0;
        performanceLogger.warn(String
                                      .format("NetworkManagment-GetDistinctEventByTac (33002235): loading in (%s seconds)",
                                              new DecimalFormat("0.00").format(timeTaken)));
    }
    @Test
    public void testGetDistinctEventByTac_108100() {
        given().filter(sessionFilter).when()
                .get("/protected/rest/networkmanagement/eventidcausecode/108100").then()
                .statusCode(200);
        long beginTime = System.currentTimeMillis();
        given().auth().form("admin", "admin", fac).filter(sessionFilter)
                .expect().statusCode(200).contentType(ContentType.JSON).when()
                .get("/protected/rest/networkmanagement/eventidcausecode/108100");
        long endTime = System.currentTimeMillis();
        double timeTaken = (endTime-beginTime)/1000.0;
        performanceLogger.warn(String
                                      .format("NetworkManagment-GetDistinctEventByTac (108100): loading in (%s seconds)",
                                              new DecimalFormat("0.00").format(timeTaken)));
    }
    
	// Worst case test. Imsi passed has largest amount of Event Cause data
	// objects associated with it
	@Test
	public void testGetEventCauseByImsiWorstCase() {
		given().filter(sessionFilter).when()
				.get("/protected/rest/IMSIEvent/191911000049149").then()
				.statusCode(200);
		long beginTime = System.currentTimeMillis();
		given().auth().form("admin", "admin", fac).filter(sessionFilter)
				.expect().statusCode(200).contentType(ContentType.JSON).when()
				.get("/protected/rest/IMSIEvent/191911000049149");
		long endTime = System.currentTimeMillis();
		double timeTaken = (endTime - beginTime) / 1000.0;
		performanceLogger.warn(String.format(
				"BaseData-GetEventCauseByImsi-24 Records : loading in (%s seconds)",
				new DecimalFormat("0.00").format(timeTaken)));
		assert (timeTaken <= MAX_QUERY_TIME);
	}
	
	@Test
	public void testGetEventCauseByImsi() {
		given().filter(sessionFilter).when()
				.get("/protected/rest/IMSIEvent/191911000563489").then()
				.statusCode(200);
		long beginTime = System.currentTimeMillis();
		given().auth().form("admin", "admin", fac).filter(sessionFilter)
				.expect().statusCode(200).contentType(ContentType.JSON).when()
				.get("/protected/rest/IMSIEvent/191911000563489");
		long endTime = System.currentTimeMillis();
		double timeTaken = (endTime - beginTime) / 1000.0;
		performanceLogger.warn(String.format(
				"BaseData-GetEventCauseByImsi-20 Records : loading in (%s seconds)",
				new DecimalFormat("0.00").format(timeTaken)));
		assert (timeTaken <= MAX_QUERY_TIME);
	}
    
    

    
    
    
    public FormAuthConfig getformAuthConfig(){
        return new FormAuthConfig(
                              "protected/j_security_check",
                              "j_username", "j_password");
    }

}
