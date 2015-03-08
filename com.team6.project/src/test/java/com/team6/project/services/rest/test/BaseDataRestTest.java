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
    public void setUp() {
        super.setUp();
        createUsers();
        fac = getformAuthConfig();
        sessionFilter = new SessionFilter();
        given().filter(sessionFilter).when().get("protected/se/index.html").then()
        .statusCode(200);
    }
    
    @Test
    public void test_login_success() {
        given().auth().form("admin", "admin", fac).filter(sessionFilter).when()
                .get("protected/index.jsp").then().body(containsString("Welcome"));

    }
    
    
//    @Test
//    public void testGetAll() {
//
//        given().auth().form("cusSer", "cusSer", fac).filter(sessionFilter)
//                .expect().statusCode(200).contentType(ContentType.JSON).when()
//                .get(buildUri("protected","rest", "IMSIEvent", "191911000456423"));
//               // .get("/protected/rest/IMSIEvent/191911000456423");
//    }

	/**
	 * This test will simply make sure the RESTfulo endpoint can be reached and
	 * that it is returning JSON even if there are no records in the database.
	 * 
	 */
//	@Test
//	public void testRestEndPoint() {
//
//		RestAssured.config = config()
//				.logConfig(new LogConfig(System.out, true));
//		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
//
//		expect().statusCode(200).contentType(ContentType.JSON).when()
//				.get(buildUri("rest", "IMSIEvent", "1")).then().log().all();
//	}
//
//	@Test
//	public void testNoInput() {
//
//		RestAssured.config = config()
//				.logConfig(new LogConfig(System.out, true));
//		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
//
//		expect().statusCode(404).when().get(buildUri("rest", "IMSIEvent", ""))
//				.then().log().all();
//	}
//
//	@Test
//	public void testInvalidInputType() {
//
//		RestAssured.config = config()
//				.logConfig(new LogConfig(System.out, true));
//		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
//
//		expect().statusCode(400).when().get(buildUri("rest", "IMSIEvent", " "))
//				.then().log().all();
//	}

	private URI buildUri(String... paths) {
		UriBuilder builder = UriBuilder.fromUri(baseURL);
		for (String path : paths) {
			builder.path(path);
		}
		return builder.build();
	}

}
