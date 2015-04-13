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
 * testing the Support Engineer Rest functions
 *
 */

@RunWith(Arquillian.class)
public class SupportEngineerRestServiceTest extends RestTest {

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
		given().auth().form("supEng", "supEng", fac).filter(sessionFilter)
				.expect().statusCode(200).when().get("protected/index.jsp");

	};

	/**
	 * 
	 * With the given tac, fromDate, toDate parameters logging in with Support
	 * Engineer (supEng) will able to process the request and checking the
	 * request conentType and the status code as expected
	 */

	@Test
	public void testCountCallFailureUsingTac() {
		long startDate = NetworkManagementRestServiceTest
				.dateConvert("2013-02-22 19:36:00");
		long endDate = NetworkManagementRestServiceTest
				.dateConvert("2013-02-22 19:37:00");

		given().queryParam("tac", 33001735).queryParam("fromDate", startDate)
				.queryParam("toDate", endDate).filter(sessionFilter).expect()
				.statusCode(200).contentType(ContentType.JSON).when()
				.get("/protected/rest/supportengineer/tac");

	}

	@Test
	public void testCountCallFailureUsingTac_WrongDates() {

		long startDate = NetworkManagementRestServiceTest
				.dateConvert("2013-02-20 21:01:00");
		long endDate = NetworkManagementRestServiceTest
				.dateConvert("2013-02-21 21:01:00");

		given().queryParam("tac", 21060800).queryParam("fromDate", endDate)
				.queryParam("toDate", startDate).filter(sessionFilter).expect()
				.statusCode(400).when()
				.get("/protected/rest/supportengineer/tac");

		startDate = NetworkManagementRestServiceTest
				.dateConvert("2018-02-20 21:01:00");
		endDate = NetworkManagementRestServiceTest
				.dateConvert("2019-02-21 21:01:00");

		given().queryParam("tac", 21060800).queryParam("fromDate", startDate)
				.queryParam("toDate", endDate).filter(sessionFilter).expect()
				.statusCode(400).when()
				.get("/protected/rest/supportengineer/tac");

		startDate = NetworkManagementRestServiceTest
				.dateConvert("2008-02-20 21:01:00");
		endDate = NetworkManagementRestServiceTest
				.dateConvert("2009-02-21 21:01:00");

		given().queryParam("tac", 21060800).queryParam("fromDate", startDate)
				.queryParam("toDate", endDate).filter(sessionFilter).expect()
				.statusCode(404).when()
				.get("/protected/rest/supportengineer/tac");

	}

	/**
	 * 
	 * With the given tac, fromDate, toDate parameters logging in with a user
	 * (Customer Service Rep. (cusSer)) who has NO PERMISSION not to be able to
	 * reach this request and get a 403 authentication error code as the status
	 * code
	 */

	@Test
	public void testCountCallFailureByTac_NoPermission() {

		sessionFilter = new SessionFilter();

		given().filter(sessionFilter).when().get("protected/index.jsp");

		given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter)
				.expect().statusCode(200).when().get("protected/index.jsp");

		given().queryParam("tac", 21060800)
				.queryParam("fromDate", "1357924500000")
				.queryParam("toDate", "1357924560000").filter(sessionFilter)
				.expect().statusCode(403).when()
				.get("/protected/rest/supportengineer/tac");

	}

	/**
	 * This test will simply make sure the RESTfull endpoint can be reached and
	 * that it is returning JSON even if there are no records in the database.
	 */
	@Test
	public void testGetAllImsiBetweeenSpecificDate() {

		long startDate = NetworkManagementRestServiceTest
				.dateConvert("2013-02-21 21:01:00");
		long endDate = NetworkManagementRestServiceTest
				.dateConvert("2013-02-21 21:01:00");
		given().auth().form("supEng", "supEng", fac)
				.queryParam("firstDate", startDate)
				.queryParam("secondDate", endDate).filter(sessionFilter)
				.expect().statusCode(200).contentType(ContentType.JSON).when()
				.get("/protected/rest/supportengineer/datequery");
	}

	@Test
	public void testGetAllImsiBetweeenSpecificDate_WrongDates() {

		long startDate = NetworkManagementRestServiceTest
				.dateConvert("2013-02-20 21:01:00");
		long endDate = NetworkManagementRestServiceTest
				.dateConvert("2013-02-21 21:01:00");

		given().queryParam("firstDate", endDate)
				.queryParam("secondDate", startDate).filter(sessionFilter)
				.expect().statusCode(400).when()
				.get("/protected/rest/supportengineer/datequery");

		startDate = NetworkManagementRestServiceTest
				.dateConvert("2018-02-20 21:01:00");
		endDate = NetworkManagementRestServiceTest
				.dateConvert("2019-02-21 21:01:00");

		given().queryParam("firstDate", startDate)
				.queryParam("secondDate", endDate).filter(sessionFilter)
				.expect().statusCode(400).when()
				.get("/protected/rest/supportengineer/datequery");

		startDate = NetworkManagementRestServiceTest
				.dateConvert("2008-02-20 21:01:00");
		endDate = NetworkManagementRestServiceTest
				.dateConvert("2009-02-21 21:01:00");

		given().queryParam("firstDate", startDate)
				.queryParam("secondDate", endDate).filter(sessionFilter)
				.expect().statusCode(404).when()
				.get("/protected/rest/supportengineer/datequery");
	}

	@Test
	public void testGetAll_NotAllowed() {
		sessionFilter = new SessionFilter();

		given().filter(sessionFilter).when().get("protected/index.jsp");

		given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter)
				.expect().statusCode(200).when().get("protected/index.jsp");

		given().filter(sessionFilter).expect().statusCode(403).when()
				.get("/protected/rest/supportengineer/datequery");

	}

	@Test
	public void testGetImsiByFailureCode() {

		Integer failureCode = 1;

		given().queryParam("failureCode", failureCode).filter(sessionFilter)
				.expect().statusCode(200).contentType(ContentType.JSON).when()
				.get("/protected/rest/supportengineer/failurecode");

	}

	@Test
	public void testGetImsiByFailureCode_WrongFailureCode() {

		String failureCode = "a";

		given().queryParam("failureCode", failureCode).filter(sessionFilter)
				.expect().statusCode(400).when()
				.get("/protected/rest/supportengineer/failurecode");

		failureCode = "test";

		given().queryParam("failureCode", failureCode).filter(sessionFilter)
				.expect().statusCode(400).when()
				.get("/protected/rest/supportengineer/failurecode");

		Integer failureCodeInt = 5;

		given().queryParam("failureCode", failureCodeInt).filter(sessionFilter)
				.expect().statusCode(404).when()
				.get("/protected/rest/supportengineer/failurecode");

	}

	@Test
	public void testGetImsiByFailureCode_NoPermission() {

		Integer failureCode = 2;

		sessionFilter = new SessionFilter();

		given().filter(sessionFilter).when().get("protected/index.jsp");

		given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter)
				.expect().statusCode(200).when().get("protected/index.jsp");

		given().queryParam("failureCode", failureCode).filter(sessionFilter)
				.expect().statusCode(403).when()
				.get("/protected/rest/supportengineer/failurecode");

	}

	@Test
	public void testGetAllFailureTypes() {

		given().filter(sessionFilter).expect().statusCode(200)
				.contentType(ContentType.JSON).when()
				.get("/protected/rest/supportengineer/failuretype");

	}

	@Test
	public void testGetAllFailureTypes_NotAllowed() {
	    
	    sessionFilter = new SessionFilter();

        given().filter(sessionFilter).when().get("protected/index.jsp");

		given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter)
				.expect().statusCode(200).when().get("protected/index.jsp");

		given().filter(sessionFilter).expect().statusCode(403).when()
				.get("/protected/rest/supportengineer/failuretype");

	}

}
