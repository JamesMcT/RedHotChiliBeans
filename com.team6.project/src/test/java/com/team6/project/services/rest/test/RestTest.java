package com.team6.project.services.rest.test;

import static com.jayway.restassured.RestAssured.config;

import java.io.File;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.config.LogConfig;
import com.team6.project.entities.User;
import com.team6.project.services.PersistenceServiceLocal;
import com.team6.project.services.QueryServiceLocal;

public abstract class RestTest {
    
    public final static String ARCHIVE_NAME = "test";
    public final static String WEBAPP_SRC = "src/main/webapp/protected";
  
    @Inject
    private PersistenceServiceLocal persistence;
    
    @Inject
    private QueryServiceLocal query;

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive a = ShrinkWrap
                .create(WebArchive.class, ARCHIVE_NAME + ".war")
                .addPackages(true, "com.team6.project.services",
                             "com.team6.project.services.rest",
                             "com.team6.project.entities",
                             "com.team6.project.readers",
                             "com.team6.project.dao",
                             "com.team6.project.dao.jpa",
                             "com.team6.project.validators")
                .addAsResource("test-persistence.xml",
                               "META-INF/persistence.xml")
                .merge(ShrinkWrap.create(GenericArchive.class)
                               .as(ExplodedImporter.class)
                               .importDirectory(WEBAPP_SRC)
                               .as(GenericArchive.class), "protected",
                       Filters.includeAll())
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"))
                .addAsWebInfResource("test-jboss-web.xml", "jboss-web.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE,
                                     ArchivePaths.create("beans.xml"));
        File[] files = Maven.resolver()
                .resolve("com.jayway.restassured:rest-assured:2.4.0")
                .withTransitivity().as(File.class);
        a.addAsLibraries(files);

        files = Maven.resolver().resolve("org.apache.poi:poi:3.11")
                .withTransitivity().as(File.class);
        a.addAsLibraries(files);

        files = Maven.resolver().resolve("org.apache.commons:commons-io:1.3.2")
                .withTransitivity().as(File.class);
        a.addAsLibraries(files);

        files = Maven.resolver()
                .resolve("commons-logging:commons-logging:1.1.3")
                .withTransitivity().as(File.class);
        a.addAsLibraries(files);

        return a;
    }
    
    @Before
    public void setUp(){
        RestAssured.config = config()
                .logConfig(new LogConfig(System.out, true));
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.basePath = ARCHIVE_NAME;
        RestAssured.port = 18080;
    }
    
    public void createUsers() {
        
        User admin = new User();
        admin.setUserId("admin");
        admin.setPassword("admin");
        admin.setRole("administrator");
        persistence.addUser(admin);
        
        User nmEng = new User();
        nmEng.setUserId("nmEng");
        nmEng.setPassword("nmEng");
        nmEng.setRole("Network Management Engineer");
        persistence.addUser(nmEng);
        
        User supEng = new User();
        supEng.setUserId("supEng");
        supEng.setPassword("supEng");
        supEng.setRole("Support Engineer");
        persistence.addUser(supEng);
        
        User cusSer = new User();
        cusSer.setUserId("cusSer");
        cusSer.setPassword("cusSer");
        cusSer.setRole("Customer Service");
        persistence.addUser(cusSer);
    }
    
   
    public FormAuthConfig getformAuthConfig(){
        return new FormAuthConfig(
                              "protected/j_security_check",
                              "j_username", "j_password");
    }


}
