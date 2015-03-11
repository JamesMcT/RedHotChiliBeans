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
        given().auth().form("nmEng", "nmEng", fac).filter(sessionFilter)
                .expect().statusCode(200).contentType(ContentType.JSON).when()
                .get("/protected/rest/networkmanagement/eventidcausecode/21060800");
        long endTime = System.currentTimeMillis();
        long timeTaken = (endTime-beginTime)/1000;
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
    
    @Test
    public void testFailedCallDurationEndpointUnauthorized(){
    	String startDate = "2013-02-19 21:01:00";
    	String endDate = "2013-02-21 21:01:00";
    	
    	//Expect to get the login page if not authenticated first
    	given().queryParam("startDate", startDate).queryParam("endDate", endDate).filter(sessionFilter).when()
        .get("/protected/rest/networkmanagement/failurecountandduration").then()
        .statusCode(200).contentType(ContentType.HTML);
    	
    	given().log().all().auth().form("cusSer", "cusSer", fac).queryParam("startDate", startDate).queryParam("endDate", endDate).filter(sessionFilter)
        .expect().statusCode(403).when()
        .get("/protected/rest/networkmanagement/failurecountandduration").then().log().all();
    }
    
    @Test
    public void testFailedCallDurationEndpoint(){
    	
    	String startDate = "2013-02-19 21:01:00";
    	String endDate = "2013-02-21 21:01:00";
    	
    	//Expect to get the login page if not authenticated first
    	given().queryParam("startDate", startDate).queryParam("endDate", endDate).filter(sessionFilter).when()
        .get("/protected/rest/networkmanagement/failurecountandduration").then()
        .statusCode(200).contentType(ContentType.HTML);
    	
    	long beginTime = System.currentTimeMillis();
    	
    	given().log().all().auth().form("nmEng", "nmEng", fac).queryParam("startDate", startDate).queryParam("endDate", endDate).filter(sessionFilter)
        .expect().statusCode(200).contentType(ContentType.JSON).when()
        .get("/protected/rest/networkmanagement/failurecountandduration").then().log().all();
    	
    	long endTime = System.currentTimeMillis();
        long timeTaken = (endTime-beginTime)/1000;
        networkManagementLogger.warn(String
                .format("NetworkManagment-failurecountandduration : loading in (%s seconds)",
                        new DecimalFormat("0.00").format(timeTaken)));
    }
   
}
