package com.team6.project.services.rest.test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.filter.session.SessionFilter;
import com.jayway.restassured.http.ContentType;

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
        given().filter(sessionFilter).when().get("protected/index.jsp").then()
                .statusCode(200);
        given().auth().form("admin", "admin", fac).filter(sessionFilter).when()
                .get("protected/index.jsp");
    }

    @Test
    public void testGetAllUser() {

        given().auth().form("admin", "admin", fac).filter(sessionFilter)
                .expect().statusCode(200).contentType(ContentType.JSON).when()
                .get("/protected/rest/usermanagement/all");

    }

    @Test
    public void testGetAllUser_NotAllowed() {
        sessionFilter = new SessionFilter();

        given().filter(sessionFilter).when().get("protected/index.jsp");

        given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter)
                .when().get("protected/index.jsp");

        given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter)
                .expect().statusCode(403).when()
                .get("/protected/rest/usermanagement/all");

    }

    @Test
    public void testGetUserByKey() {

        given().auth().form("admin", "admin", fac).filter(sessionFilter)
                .expect().statusCode(200).contentType(ContentType.JSON).when()
                .get("/protected/rest/usermanagement/admin").then()
                .body(containsString("admin"));

    }

    @Test
    public void testGetUserByKey_NotAllowed() {

        sessionFilter = new SessionFilter();

        given().filter(sessionFilter).when().get("protected/index.jsp");

        given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter)
                .when().get("protected/index.jsp");

        given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter)
                .expect().statusCode(403).when()
                .get("/protected/rest/usermanagement/admin");

    }

    @Test
    public void testAddNewUser() {
        String myJson = "{\"userId\":\"admin2\",\"password\":\"password\",\"role\":\"administrator\"}";

        given().auth().form("admin", "admin", fac).filter(sessionFilter)
                .contentType(ContentType.JSON).body(myJson).expect()
                .statusCode(200).when().with()
                .header("Origin", "")
                .post("/protected/rest/usermanagement/add");

    }
    
    @Test
    public void testChangeNewUser() {
        String myJson = "{\"userId\":\"admin3\",\"password\":\"password\",\"role\":\"administrator\"}";

        given().auth().form("admin", "admin", fac).filter(sessionFilter)
                .contentType(ContentType.JSON).body(myJson).expect()
                .statusCode(200).when().with()
                .header("Origin", "")
                .post("/protected/rest/usermanagement/update");

    }

}
