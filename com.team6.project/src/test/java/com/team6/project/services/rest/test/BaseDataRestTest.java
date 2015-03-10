package com.team6.project.services.rest.test;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.filter.session.SessionFilter;
import com.jayway.restassured.http.ContentType;

import static com.jayway.restassured.RestAssured.given;


/**
 * 
 * @author Sabee D14125306
 *
 */


/**
 * 
 * testing the BaseDataRest funcions
 *
 */

@RunWith(Arquillian.class)
public class BaseDataRestTest extends RestTest{
	
	private FormAuthConfig fac;
	private SessionFilter sessionFilter;
	
	@Before
	public void setUp() throws InterruptedException {
		super.setUp();
		createUsers();
		fac = getformAuthConfig();
		sessionFilter = new SessionFilter();
		given()
			.filter(sessionFilter)
			.when()
			.get("/protected/rest/tac")			
			.then()
			.statusCode(200);
		
	};
	
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
			.get("/protected/rest/tac");
		
		
	}
	
	@Test
	public void testCountCallFailureByTac_NoPermission() {
		

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
			.get("/protected/rest/tac");
		
		
	}
	
	
	
	
	
}
