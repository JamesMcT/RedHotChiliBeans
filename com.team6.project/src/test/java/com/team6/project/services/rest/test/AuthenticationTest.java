package com.team6.project.services.rest.test;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.net.URI;

import javax.ejb.EJB;
import javax.ws.rs.core.UriBuilder;

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
import com.team6.project.dao.UserDAO;
import com.team6.project.entities.User;

@RunWith(Arquillian.class)
public class AuthenticationTest extends RestTest {

    private FormAuthConfig fac;
    private SessionFilter sessionFilter;

    @Before
    public void setUp() {
        super.setUp();
        createUsers();
        fac = getformAuthConfig();
        sessionFilter = new SessionFilter();
        given().filter(sessionFilter).when().get("protected/index.jsp").then()
                .statusCode(200);
    }

    @Test
    public void test_login_success() {
        given().auth().form("admin", "admin", fac).filter(sessionFilter).when()
                .get("protected/index.jsp").then().body(containsString("Welcome"));

    }

    @Test
    public void test_login_fail() {

        given().auth().form("user", "password", fac).filter(sessionFilter)
                .when().get("protected/index.jsp").then()
                .body(containsString("<title>Login Form</title>"));

    }
}
