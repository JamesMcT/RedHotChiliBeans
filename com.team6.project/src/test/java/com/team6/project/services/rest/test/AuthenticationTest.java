package com.team6.project.services.rest.test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.filter.session.SessionFilter;

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
