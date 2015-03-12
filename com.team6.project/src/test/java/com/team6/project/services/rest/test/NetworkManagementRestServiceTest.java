package com.team6.project.services.rest.test;

import static com.jayway.restassured.RestAssured.given;

import java.text.DecimalFormat;

import org.apache.log4j.Logger;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.filter.session.SessionFilter;
import com.jayway.restassured.http.ContentType;
@RunWith(Arquillian.class)
public class NetworkManagementRestServiceTest extends RestTest {

    private FormAuthConfig fac;
    private SessionFilter sessionFilter;
    private Logger networkManagementLogger = org.apache.log4j.Logger.getLogger(NetworkManagementRestServiceTest.class);

    @Before
    public void setUp() throws InterruptedException {
        super.setUp();
        createUsers();
        fac = getformAuthConfig();
        sessionFilter = new SessionFilter();
        
    }

    @Test
    public void testGetDistinctEventByTac() {
        given().filter(sessionFilter).when()
                .get("/protected/rest/networkmanagement/eventidcausecode/21060800").then()
                .statusCode(200);
        long beginTime = System.currentTimeMillis();
        given().auth().form("admin", "admin", fac).filter(sessionFilter)
                .expect().statusCode(200).contentType(ContentType.JSON).when()
                .get("/protected/rest/networkmanagement/eventidcausecode/21060800");
        long endTime = System.currentTimeMillis();
        double timeTaken = (endTime-beginTime)/1000.0;
        networkManagementLogger.warn(String
                                      .format("NetworkManagment-GetDistinctEventByTac : loading in (%s seconds)",
                                              new DecimalFormat("0.00").format(timeTaken)));
    }

    @Test
    public void testGetAllUser_NotAllowed() {
        given().filter(sessionFilter).when()
                .get("/protected/rest/networkmanagement/eventidcausecode/33001735").then()
                .statusCode(200);

        given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter)
                .expect().statusCode(403).when()
                .get("/protected/rest/networkmanagement/eventidcausecode/33001735");

    }
    
   
}
