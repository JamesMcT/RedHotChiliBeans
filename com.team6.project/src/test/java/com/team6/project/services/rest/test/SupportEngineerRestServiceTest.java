package com.team6.project.services.rest.test;



import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.filter.session.SessionFilter;
import com.jayway.restassured.http.ContentType;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;


/**
 * 
 * @author Sabee D14125306
 *
 */


/**
 * 
 * testing the BaseDataRest functions
 *
 */

@RunWith(Arquillian.class)
public class SupportEngineerRestServiceTest extends RestTest{
	
	private FormAuthConfig fac;
	private SessionFilter sessionFilter;
	private long fromTime;
	private long toTime;
	
	@Before
	public void setUp() throws InterruptedException {
		super.setUp();
		createUsers();
		fac = getformAuthConfig();
		sessionFilter = new SessionFilter();
		given().filter(sessionFilter).when().get("protected/index.jsp").then()
        .statusCode(200);
		given().auth().form("supEng", "supEng", fac).filter(sessionFilter).when()
        .get("protected/index.jsp");
		
	};
	
	
	/**
	 * 
	 * Testing the large database (60 thousand records) with the given tac, fromDate, toDate parameters
	 * logging in with Support Engineer (supEng) will able to process the request
	 * and checking the request conentType and the status code as expected
	 */
	
	
	@Test
	public void testCountCallFailureByTac() {
		
		given()
			.auth()
			.form("supEng", "supEng", fac)
			.queryParam("tac" ,21060800)
			.queryParam("fromDate", "1357924500000")
			.queryParam("toDate", "1357924560000")			
			.filter(sessionFilter)
			.expect()
			.statusCode(200)
			.contentType(ContentType.JSON)
			.when()
			.get("/protected/rest/supportengineer/tac");
		
		
	}
	

	/**
	 * 
	 * Testing the large database (60 thousand records) with the given tac, fromDate, toDate parameters
	 * logging in with a user (Customer Service Rep. (cusSer)) who has NO PERMISSION
	 * not to be able to reach this request 
	 * and get a 403 authentication error code as the status code
	 */
	
	@Test
	public void testCountCallFailureByTac_NoPermission() {
	    
	    sessionFilter = new SessionFilter();

        given().filter(sessionFilter).when().get("protected/index.jsp");

        given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter)
                .when().get("protected/index.jsp");
		
		given()
			.auth()
			.form("cusSer", "cusSer", fac)
			.queryParam("tac" ,21060800)
			.queryParam("fromDate", "1357924500000")
			.queryParam("toDate", "1357924560000")			
			.filter(sessionFilter)
			.expect()
			.statusCode(403)
			.when()
			.get("/protected/rest/supportengineer/tac");
		
	}
	
	
}
