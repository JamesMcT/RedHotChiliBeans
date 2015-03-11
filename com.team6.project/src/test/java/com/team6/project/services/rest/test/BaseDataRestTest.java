package com.team6.project.services.rest.test;

import java.io.File;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import net.sf.ehcache.search.expression.EqualTo;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.config.LogConfig;
import com.jayway.restassured.filter.session.SessionFilter;
import com.jayway.restassured.http.ContentType;
import com.team6.project.entities.EventCause;

/**
 * Test class for all REST services associated with BaseData class.
 * 
 * @author James
 *
 */

@RunWith(Arquillian.class)
public class BaseDataRestTest extends RestTest {

	private final static String ARCHIVE_NAME = "test";
	private final String baseURL = "http://localhost:8080/" + ARCHIVE_NAME;

	public BaseDataRestTest() {
	}

	private FormAuthConfig fac;
	private SessionFilter sessionFilter;

	@Before
	public void setUp() throws InterruptedException {
		super.setUp();
		createUsers();
		fac = getformAuthConfig();
		sessionFilter = new SessionFilter();

	}

	/**
	 * Test if the restful EventCauseSearch.html page can be accessed by a
	 * customer service representative. This is the page which currently host's
	 * the GUI for the findEventCauseByIMSI named query.
	 */
	@Test
	public void testEventCausePage() {

		given().filter(sessionFilter).when()
				.get("/protected/csr/EventCauseSearch.html").then()
				.statusCode(200);

		given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter)
				.when().get("protected/csr/EventCauseSearch.html").then()
				.body(containsString("<title>Customer Service Rep</title>"));

	}

	/**
	 * Test if getEventCause Query returns valid status code and JSON response.
	 */
	@Test
	public void testGetEventCause() {

		given().filter(sessionFilter).expect().statusCode(200).when()
				.get("protected/rest/IMSIEvent/191911000423586"); // This IMSI
																	// value has
																	// been
																	// taken
																	// from Test
																	// Data
																	// which is
																	// loaded
																	// into test
																	// database.

		given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter)
				.expect().statusCode(200).when()
				.get("protected/rest/IMSIEvent/191911000423586").then()
				.contentType(ContentType.JSON);

	}

	/**
	 * Ensure no input will return the correct HTML status code.
	 */

	@Test
	public void testNoInput() {

		given().filter(sessionFilter).expect().statusCode(200).when()
				.get("protected/rest/IMSIEvent/191911000423586");

		given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter)
				.expect().statusCode(404).when().get("procted/rest/IMSIEvent/");

	}

	/**
	 * Test that invalid input data returns Http error code 400 = bad request.
	 */
	@Test
	public void testInvalidInputType() {

		given().filter(sessionFilter).expect().statusCode(200).when()
				.get("protected/rest/IMSIEvent/A");

		given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter)
				.expect().statusCode(400).when()
				.get("protected/rest/IMSIEvent/A");
	}
	
//	@Test
//	public void testInvalidUserType() {
//
//		given().filter(sessionFilter).expect().statusCode(200).when()
//				.get("/protected/csr/EventCauseSearch.html");
//
//		given().auth().form("aaa", "aaa", fac).filter(sessionFilter)
//				.expect().when()
//				.get("/protected/csr/EventCauseSearch.html").then().statusCode(403);
//	}

}
