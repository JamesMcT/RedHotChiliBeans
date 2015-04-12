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

/**
 * Test class for all REST services associated with Customer Service Rep class.
 * 
 * @author James
 *
 */

@RunWith(Arquillian.class)
public class CustomerServiceRestTest extends RestTest {


	public CustomerServiceRestTest() {
	}

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
		   given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter).when()
           .get("protected/index.jsp");

	}

	/**
	 * Test if the restful EventCauseSearch.html page can be accessed by a
	 * customer service representative. This is the page which currently host's
	 * the GUI for the findEventCauseByIMSI named query.
	 */
	@Test
	public void testEventCausePage() {
		given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter)
				.when().get("protected/csr/EventCauseSearch.html").then()
				.body(containsString("<title>Red Hot Chilli Beans</title>"));

	}

	/**
	 * Test if getEventCause Query returns valid status code and JSON response.
	 */
	@Test
	public void testGetEventCause() {
		given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter)
				.expect().statusCode(200).when()
				.get("protected/rest/customerservice/191911000423586").then()
				.contentType(ContentType.JSON);

	}

	/**
	 * Ensure no input will return the correct HTML status code.
	 */

	@Test
	public void testNoInput() {
		given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter)
				.expect().statusCode(404).when().get("procted/rest/customerservice/");

	}

	/**
	 * Test that invalid input data returns Http error code 400 = bad request.
	 */
	@Test
	public void testInvalidInputType() {
		given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter)
				.expect().statusCode(400).when()
				.get("protected/rest/customerservice/A");
	}
	
	/**
     * Test valid IMSI for UniqueCauseCodePage
     */
	@Test 
	public void testUniqueCauseCodePage(){
	     given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter)
        .expect().statusCode(200).when()
        .get("protected/rest/customerservice/uniqueec/191911000423586").then()
        .contentType(ContentType.JSON);

	}
	



}
