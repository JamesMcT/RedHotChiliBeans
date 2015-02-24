package com.team6.project.services.rest.test;

import java.io.File;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

import org.junit.Test;
import org.junit.runner.RunWith;

import static com.jayway.restassured.RestAssured.*;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.LogConfig;
import com.jayway.restassured.http.ContentType;

@RunWith(Arquillian.class)
public class UserEquipmentRestTest {

	private final static String ARCHIVE_NAME = "test";
	private final String baseURL = "http://localhost:8080/" + ARCHIVE_NAME;
	
	public UserEquipmentRestTest() {}
	
	@Deployment
    public static Archive<?> createDeployment() {
		WebArchive a = ShrinkWrap
                .create(WebArchive.class, ARCHIVE_NAME+".war")
                .addPackages(true, 	"com.team6.project.services",
                					"com.team6.project.services.rest",
                					"com.team6.project.entities", 
                					"com.team6.project.readers", 
                					"com.team6.project.dao",
                					"com.team6.project.dao.jpa",
                					"com.team6.project.validators")
                .addAsResource("test-persistence.xml","META-INF/persistence.xml")
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"))
                .addAsWebInfResource(EmptyAsset.INSTANCE,ArchivePaths.create("beans.xml"));
		
		File[] files = Maven.resolver().resolve("com.jayway.restassured:rest-assured:2.4.0").withTransitivity().as(File.class);
		//files = removeGroovyXml(files);
        a.addAsLibraries(files);

        files = Maven.resolver().resolve("org.apache.poi:poi:3.11").withTransitivity().as(File.class);
        a.addAsLibraries(files);
        
        files = Maven.resolver().resolve("org.apache.commons:commons-io:1.3.2").withTransitivity().as(File.class);
        a.addAsLibraries(files);
        
        files = Maven.resolver().resolve("commons-logging:commons-logging:1.1.3").withTransitivity().as(File.class);
        a.addAsLibraries(files);
        
        return a;
    }

	/**
	 * This test will simply make sure the RESTfulo endpoint can be reached and that it is returning JSON
	 * even if there are no records in the database.
	 * 
	 */
	@Test
	public void testGetAll(){
		 
		RestAssured.config = config().logConfig(new LogConfig(System.out, true));
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		System.out.println();
		System.out.println();
		
		expect().statusCode(200)
		 		.contentType(ContentType.JSON)
		 		.when()
		 		.get(buildUri("rest", "userequipment", "all")).then().log().all();
		
		System.out.println();
		System.out.println();
	 }

	 private URI buildUri(String... paths) {
	        UriBuilder builder = UriBuilder.fromUri(baseURL);
	        for (String path : paths) {
	            builder.path(path);
	        }
	        return builder.build();
	 }
	 
}