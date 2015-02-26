/*package com.team6.project.services.rest.test;

import static com.jayway.restassured.RestAssured.config;
import static com.jayway.restassured.RestAssured.given;

import java.io.File;
import java.net.URI;

import javax.ejb.EJB;
import javax.ws.rs.core.UriBuilder;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.config.LogConfig;
import com.team6.project.dao.UserDAO;
import com.team6.project.entities.User;

@RunWith(Arquillian.class)
public class AuthenticationTest {

    private final static String ARCHIVE_NAME = "test";
    private final static String baseURL = "http://localhost:8080/"
            + ARCHIVE_NAME;
    private final static String WEBAPP_SRC = "src/main/webapp/protected";

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
                .addAsResource("META-INF/persistence.xml")
                .addAsWebResource(new File(WEBAPP_SRC, "login.html"),
                                  ArchivePaths.create("protected/login.html"))
                .addAsWebResource(new File(WEBAPP_SRC, "index.html"),
                                  ArchivePaths.create("protected/index.html"))
                .addAsWebResource(new File(WEBAPP_SRC, "error.html"),
                                  ArchivePaths.create("protected/error.html"))
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"))
                .setWebXML(new File("src/main/webapp/WEB-INF/jboss-web.xml"))
                .addAsWebInfResource(EmptyAsset.INSTANCE,
                                     ArchivePaths.create("beans.xml"));
        File[] files = Maven.resolver()
                .resolve("com.jayway.restassured:rest-assured:2.4.0")
                .withTransitivity().as(File.class);
        // files = removeGroovyXml(files);
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

    @EJB
    private UserDAO userDao;
    
    private static FormAuthConfig fac;

    @Before
    public void setup() {
        RestAssured.config = config()
                .logConfig(new LogConfig(System.out, true));
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        System.err.println(userDao.getUserByKey("cristiana").getPassword());
        //User p = new User();
        //p.setUserId("cristiana");
        //p.setPassword("password");
        //p.setRole("administrator");
        //userDao.addUser(p);
    }

    @Test
    public void test_login_success() {
        given().auth()
                .form("cristiana",
                      "password",
                      new FormAuthConfig(ARCHIVE_NAME
                              + "/protected/j_security_check", "j_username",
                                         "j_password")).expect()
                .statusCode(200).when()
                .get(buildUri("protected", "index.html"));
    }

    @Test
    public void test_login_failure() {
        given().auth()
                .form("fail",
                      "hard",
                      new FormAuthConfig(ARCHIVE_NAME + "/protected/j_security_check", "j_username",
                                         "j_password")).expect()
                .statusCode(200).when()
                .get(buildUri("protected", "index.html"));
    }

    private URI buildUri(String... paths) {
        UriBuilder builder = UriBuilder.fromUri(baseURL);
        for (String path : paths) {
            builder.path(path);
        }
        return builder.build();
    }

}
*/