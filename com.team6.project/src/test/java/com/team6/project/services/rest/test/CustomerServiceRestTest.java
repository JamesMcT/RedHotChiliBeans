package com.team6.project.services.rest.test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;
import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.filter.session.SessionFilter;
import com.jayway.restassured.http.ContentType;
import com.team6.project.services.QueryServiceLocal;
import com.team6.project.services.rest.performance.test.PerformanceRestTestImsi;

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

	@Inject
	private QueryServiceLocal queryService;

	@Before
	public void setUp() throws InterruptedException {
		super.setUp();
		createUsers();
		fac = getformAuthConfig();
		sessionFilter = new SessionFilter();
		given().filter(sessionFilter).when().get("protected/index.jsp").then()
				.statusCode(200);
		given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter)
				.when().get("protected/index.jsp");

	}

	/**
	 * Test if the restful EventCauseSearch.html page can be accessed by a
	 * customer service representative. This is the page which currently host's
	 * the GUI for the findEventCauseByIMSI named query.
	 */
	@Test
	public void testEventCausePage() {
		given().filter(sessionFilter)
				.when().get("protected/csr/EventCauseSearch.html").then()
				.body(containsString("<title>Red Hot Chilli Beans</title>"));

	}

	/**
	 * Test if getEventCause Query returns valid status code and JSON response.
	 */
	@Test
	public void testGetEventCause() {
		given().filter(sessionFilter)
				.expect().statusCode(200).when()
				.get("protected/rest/customerservice/191911000423586").then()
				.contentType(ContentType.JSON);

	}

	/**
	 * Ensure no input will return the correct HTML status code.
	 */

	@Test
	public void testNoInput() {
		given().filter(sessionFilter)
				.expect().statusCode(404).when()
				.get("procted/rest/customerservice/");

	}

	/**
	 * Test that invalid input data returns Http error code 400 = bad request.
	 */
	@Test
	public void testInvalidInputType() {
		given().filter(sessionFilter)
				.expect().statusCode(400).when()
				.get("protected/rest/customerservice/A");
	}

	/**
	 * Test valid IMSI for UniqueCauseCodePage
	 */
	@Test
	public void testUniqueCauseCodePage() {
		given().filter(sessionFilter)
				.expect().statusCode(200).when()
				.get("protected/rest/customerservice/uniqueec/191911000423586")
				.then().contentType(ContentType.JSON);

	}

	/**
	 * Test that FailureCount.jsp page can be accessed successfully by Customer
	 * Service Representative.
	 */
	@Test
	public void testFailureCountPage() {
		given().filter(sessionFilter)
				.when().get("protected/csr/FailureCount.jsp").then()
				.body(containsString("<title>Red Hot Chilli Beans</title>"));

	}

	/**
	 * Test FailureCount Query returns valid status code and JSON response with
	 * a valid input. The date range has been set to 2010-05-30 to systems current date.
	 */
	@Test
	public void testFailureCount() {

		Date d = new Date();
		long endDate = d.getTime();

		given().filter(sessionFilter)
				.expect()
				.statusCode(200)
				.when()
				.get("protected/rest/customerservice/countImsi?imsi=191911000001049"
						+ "&startDate=1275239700000&endDate="+endDate);
	}

	/**
	 * Test FailureCount Query returns valid status code and JSON response with
	 * a valid input. The date range has been set to 2010-05-30 in one minute period
	 * in which we have no data in the database
	 */	
	@Test
	public void testFailureCount_emptySet() {

		Date d = new Date();
		long endDate = d.getTime();

		given().filter(sessionFilter)
				.expect()
				.statusCode(404)
				.when()
				.get("protected/rest/customerservice/countImsi?imsi=191911000001049"
						+ "&startDate=1275239700000&endDate==1275239760000");
	}
	
	
	/**
	 * Test that invalid data will return the correct HTTP status code (400 =
	 * Bad Request)
	 */
	@Test
	public void testfailureCountInvalidInput() {
		given().auth()
				.form("cusSer", "cusSer", fac)
				.filter(sessionFilter)
				.expect()
				.statusCode(400)
				.when()
				.get("protected/rest/customerservice/count?imsi=&startDate=1361207700000&endDate=1361207700000");

	}

}
