package com.team6.project.services.rest.test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import javax.json.Json;
import javax.json.JsonObject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.filter.session.SessionFilter;
import com.jayway.restassured.http.ContentType;
import com.team6.project.entities.User;

@RunWith(Arquillian.class)
public class UserManagementRestServiceTest extends RestTest {

    private FormAuthConfig fac;
    private SessionFilter sessionFilter;

    @Before
    public void setUp() throws InterruptedException {
        super.setUp();
        createUsers();
        fac = getformAuthConfig();
        sessionFilter = new SessionFilter();
    }

    @Test
    public void testGetAllUser() {
        given().filter(sessionFilter).when()
                .get("/protected/rest/usermanagement/all").then()
                .statusCode(200);

        given().auth().form("admin", "admin", fac).filter(sessionFilter)
                .expect().statusCode(200).contentType(ContentType.JSON).when()
                .get("/protected/rest/usermanagement/all");

    }

    @Test
    public void testGetAllUser_NotAllowed() {
        given().filter(sessionFilter).when()
                .get("/protected/rest/usermanagement/all").then()
                .statusCode(200);

        given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter)
                .expect().statusCode(403).when()
                .get("/protected/rest/usermanagement/all");

    }

    @Test
    public void testGetUserByKey() {
        given().filter(sessionFilter).when()
                .get("/protected/rest/usermanagement/admin").then()
                .statusCode(200);

        given().auth().form("admin", "admin", fac).filter(sessionFilter)
                .expect().statusCode(200).contentType(ContentType.JSON).when()
                .get("/protected/rest/usermanagement/admin").then()
                .body(containsString("admin"));

    }

    @Test
    public void testGetUserByKey_NotAllowed() {
        given().filter(sessionFilter).when()
                .get("/protected/rest/usermanagement/admin").then()
                .statusCode(200);

        given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter)
                .expect().statusCode(403).when()
                .get("/protected/rest/usermanagement/admin");

    }


/*    @Test
    public void testAddNewUser() {
        String myJson = "{\"userId\":\"admin2\",\"password\":\"password\",\"role\":\"administrator\"}";

        given().auth().form("admin", "admin", fac).filter(sessionFilter)
                .contentType(ContentType.JSON).body(myJson).expect()
                .statusCode(200).when()
                .post("/protected/rest/usermanagement/add");
        
        given().auth().form("admin", "admin", fac).filter(sessionFilter)
        .contentType(ContentType.JSON).body(myJson).expect()
        .statusCode(200).when()
        .post("/protected/rest/usermanagement/add");

    }*/
    
   

}
