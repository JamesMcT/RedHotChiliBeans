package com.team6.project.services.rest.test;

import static com.jayway.restassured.RestAssured.config;
import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;

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

@RunWith(Arquillian.class)
public class PerformanceRestTest {

    public final static String ARCHIVE_NAME = "test";
    public final static String WEBAPP_SRC = "src/main/webapp/protected";
    public final static double MAX_QUERY_TIME = 100.0;

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
    private Logger performanceLogger = org.apache.log4j.Logger
            .getLogger(PerformanceRestTest.class);
    private String startDate = "2012-01-01 00:00:00";// "2013-02-01 21:01:00";
    private String endDate = "2015-01-01 00:00:00";// "2013-03-21 21:01:00";
    private String[] tacs = { "100100", "101500", "102700", "103300", "103600",
            "104600", "105500", "106900", "108100", "21060800", "33000153",
            "33000253", "33000753", "33001235", "33001735", "33001953",
            "33002135", "33002235", "33002353", "33002535", "33002635" };

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
    public void testGetDistinctEventByTac() {
        for (int i = 0; i < tacs.length; i++) {
            given().filter(sessionFilter)
                    .when()
                    .get("/protected/rest/networkmanagement/eventidcausecode/"
                            + tacs[i]).then().statusCode(200);

            long beginTime = System.currentTimeMillis();

            given().auth()
                    .form("admin", "admin", fac)
                    .filter(sessionFilter)
                    .expect()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .when()
                    .get("/protected/rest/networkmanagement/eventidcausecode/"
                            + tacs[i]);

            long endTime = System.currentTimeMillis();

            double timeTaken = (endTime - beginTime) / 1000.0;

            assertTrue(timeTaken < MAX_QUERY_TIME);

            performanceLogger
                    .warn(String
                            .format("NetworkManagment-GetDistinctEventByTac: loading in (%s seconds)",
                                    new DecimalFormat("0.00").format(timeTaken)));

            sessionFilter = new SessionFilter();
        }

    }

    /*
     * @Inject private BaseDataDAO basedatadao; /* @Test public void
     * testGetEventCauseByImsi() {
     * 
     * ArrayList<BigInteger> allImsi = (ArrayList<BigInteger>)
     * basedatadao.getAllImsi();
     * 
     * given().filter(sessionFilter).when()
     * .get("/protected/rest/customerservice/191911000002897").then()
     * .statusCode(200);
     * 
     * given().auth().form("admin", "admin", fac).filter(sessionFilter)
     * .expect().statusCode(200).contentType(ContentType.JSON).when()
     * .get("/protected/rest/customerservice/191911000002897");
     * 
     * for(int i = 0 ; i<=500; i++){
     * 
     * String input = allImsi.get(i).toString();
     * 
     * long beginTime = System.currentTimeMillis();
     * given().filter(sessionFilter)
     * .expect().statusCode(200).contentType(ContentType.JSON).when()
     * .get("/protected/rest/customerservice/"+input);
     * 
     * long endTime = System.currentTimeMillis(); double timeTaken = (endTime -
     * beginTime) / 1000.0;
     * 
     * assert (timeTaken <= MAX_QUERY_TIME);
     * 
     * }
     * 
     * }
     */

    @Test
    public void testFailedCallDurationEndpoint() {

        // Expect to get the login page if not authenticated first
      /*  given().queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .filter(sessionFilter)
                .when()
                .get("/protected/rest/networkmanagement/failurecountandduration")
                .then().statusCode(200).contentType(ContentType.HTML);*/

        long beginTime = System.currentTimeMillis();

        given().auth()
                .form("admin", "admin", fac)
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .filter(sessionFilter)
                .expect()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .when()
                .get("/protected/rest/networkmanagement/failurecountandduration");

        long endTime = System.currentTimeMillis();
        double timeTaken = (endTime - beginTime) / 1000.0;
        performanceLogger
                .warn(String
                        .format("NetworkManagment-failurecountandduration : loading in (%s seconds)",
                                new DecimalFormat("0.000").format(timeTaken)));
    }

    @Test
    public void testCountCallFailureByTac() {
       /* given().filter(sessionFilter).when()
                .get("/protected/rest/basedata/tac").then().statusCode(200);*/
        long beginTime = System.currentTimeMillis();

        given().auth().form("admin", "admin", fac).queryParam("tac", 21060800)
                .queryParam("fromDate", "2013-01-11 17:15:00")
                .queryParam("toDate", "2013-01-11 17:34:00")
                .filter(sessionFilter).expect().statusCode(200)
                .contentType(ContentType.JSON).when()
                .get("/protected/rest/basedata/tac");

        long endTime = System.currentTimeMillis();
        double timeTaken = (endTime - beginTime) / 1000.0;
        performanceLogger
                .warn(String
                        .format("SupportEngineer-CountCallFailureByTac : loading in (%s seconds)",
                                new DecimalFormat("0.000").format(timeTaken)));
    }

    @Test
    public void testGetAll() {
        given().filter(sessionFilter).when()
                .get("/protected/rest/basedata/datequery").then()
                .statusCode(200);

        long beginTime = System.currentTimeMillis();

        given().auth().form("admin", "admin", fac)
                .queryParam("firstDate", startDate)
                .queryParam("secondDate", endDate).filter(sessionFilter)
                .expect().statusCode(200).contentType(ContentType.JSON).when()
                .get("/protected/rest/basedata/datequery");
        long endTime = System.currentTimeMillis();
        double timeTaken = (endTime - beginTime) / 1000.0;
        performanceLogger
                .warn(String
                        .format("SupportEngineer-getAllIMSIinRangeTime : loading in (%s seconds)",
                                new DecimalFormat("0.000").format(timeTaken)));
    }

    public FormAuthConfig getformAuthConfig() {
        return new FormAuthConfig("protected/j_security_check", "j_username",
                                  "j_password");
    }

}
