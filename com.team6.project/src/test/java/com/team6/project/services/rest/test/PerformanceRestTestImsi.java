package com.team6.project.services.rest.test;

import static com.jayway.restassured.RestAssured.config;
import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.math.BigInteger;
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
    /*
    @Test
    public void getUniqueEventCauseByImsi_1() throws InterruptedException {
        ArrayList<BigInteger> allImsi = (ArrayList<BigInteger>) queryService
                .getAllImsi();
        int j = 0;
        while (j < allImsi.size()) {
            for (int i = 0; i < 200; i++) {
                long beginTime = System.currentTimeMillis();
                given().auth()
                        .form("admin", "admin", fac)
                        .filter(sessionFilter)
                        .expect()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .when()
                        .get("/protected/rest/customerservice/uniqueec/"
                                + allImsi.get(i));
                long endTime = System.currentTimeMillis();
                double timeTaken = (endTime - beginTime) / 1000.0;
                assertTrue(timeTaken < MAX_QUERY_TIME);

            }
            j = j+200;
            Thread.sleep(50);

        }
    }
*/
    /*
     * @Test public void getUniqueEventCauseByImsi_2() { ArrayList<BigInteger>
     * allImsi = (ArrayList<BigInteger>) queryService .getAllImsi(); for (int i
     * = 150; i < 1000; i++) { long beginTime = System.currentTimeMillis();
     * given().auth() .form("admin", "admin", fac) .filter(sessionFilter)
     * .when() .get("/protected/rest/customerservice/uniqueec/" +
     * allImsi.get(i)); long endTime = System.currentTimeMillis(); double
     * timeTaken = (endTime - beginTime) / 1000.0; assertTrue(timeTaken <
     * MAX_QUERY_TIME); } }
     * 
     * @Test public void getUniqueEventCauseByImsi_3() { ArrayList<BigInteger>
     * allImsi = (ArrayList<BigInteger>) queryService .getAllImsi(); for (int i
     * = 1000; i < 2000; i++) { long beginTime = System.currentTimeMillis();
     * given().auth() .form("admin", "admin", fac) .filter(sessionFilter)
     * .expect() .statusCode(200) .contentType(ContentType.JSON) .when()
     * .get("/protected/rest/customerservice/uniqueec/" + allImsi.get(i)); long
     * endTime = System.currentTimeMillis(); double timeTaken = (endTime -
     * beginTime) / 1000.0; assertTrue(timeTaken < MAX_QUERY_TIME); } }
     * 
     * @Test public void getUniqueEventCauseByImsi_4() { ArrayList<BigInteger>
     * allImsi = (ArrayList<BigInteger>) queryService .getAllImsi(); for (int i
     * = 2000; i < 3000; i++) { long beginTime = System.currentTimeMillis();
     * given().auth() .form("admin", "admin", fac) .filter(sessionFilter)
     * .expect() .statusCode(200) .contentType(ContentType.JSON) .when()
     * .get("/protected/rest/customerservice/uniqueec/" + allImsi.get(i)); long
     * endTime = System.currentTimeMillis(); double timeTaken = (endTime -
     * beginTime) / 1000.0; assertTrue(timeTaken < MAX_QUERY_TIME); } }
     * 
     * @Test public void getUniqueEventCauseByImsi_5() { ArrayList<BigInteger>
     * allImsi = (ArrayList<BigInteger>) queryService .getAllImsi(); for (int i
     * = 3000; i < 4000; i++) { long beginTime = System.currentTimeMillis();
     * given().auth() .form("admin", "admin", fac) .filter(sessionFilter)
     * .expect() .statusCode(200) .contentType(ContentType.JSON) .when()
     * .get("/protected/rest/customerservice/uniqueec/" + allImsi.get(i)); long
     * endTime = System.currentTimeMillis(); double timeTaken = (endTime -
     * beginTime) / 1000.0; assertTrue(timeTaken < MAX_QUERY_TIME); } }
     * 
     * @Test public void getUniqueEventCauseByImsi_6() { ArrayList<BigInteger>
     * allImsi = (ArrayList<BigInteger>) queryService .getAllImsi(); for (int i
     * = 4000; i < 5000; i++) { long beginTime = System.currentTimeMillis();
     * given().auth() .form("admin", "admin", fac) .filter(sessionFilter)
     * .expect() .statusCode(200) .contentType(ContentType.JSON) .when()
     * .get("/protected/rest/customerservice/uniqueec/" + allImsi.get(i)); long
     * endTime = System.currentTimeMillis(); double timeTaken = (endTime -
     * beginTime) / 1000.0; assertTrue(timeTaken < MAX_QUERY_TIME); } }
     */
    public FormAuthConfig getformAuthConfig() {
        return new FormAuthConfig("protected/j_security_check", "j_username",
                                  "j_password");

    }

}
