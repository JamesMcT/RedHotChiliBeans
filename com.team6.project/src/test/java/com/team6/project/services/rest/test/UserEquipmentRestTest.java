package com.team6.project.services.rest.test;

import static com.jayway.restassured.RestAssured.given;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.filter.session.SessionFilter;
import com.jayway.restassured.http.ContentType;

@RunWith(Arquillian.class)
public class UserEquipmentRestTest extends RestTest {

    private FormAuthConfig fac;
    private SessionFilter sessionFilter;

    @Before
    public void setUp() {
        super.setUp();
        createUsers();
        fac = getformAuthConfig();
        sessionFilter = new SessionFilter();
        given().filter(sessionFilter).when().get("/protected/rest/userequipment/all").then()
        .statusCode(200);
    }

    /**
     * This test will simply make sure the RESTfulo endpoint can be reached and
     * that it is returning JSON even if there are no records in the database.
     * 
     */
   @Test
    public void testGetAll() {

        given().auth().form("admin", "admin", fac).filter(sessionFilter)
                .expect().statusCode(200).contentType(ContentType.JSON).when()
                .get("/protected/rest/userequipment/all");

      
    }

}