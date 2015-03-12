package com.team6.project.services.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import javax.ejb.EJB;

import org.apache.commons.io.FileUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.team6.project.entities.EventCause;
import com.team6.project.entities.EventCausePK;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.OperatorCountryPK;
import com.team6.project.entities.UserEquipment;
import com.team6.project.readers.EventCauseReader;
import com.team6.project.readers.FailureTypeReader;
import com.team6.project.readers.OperatorCountryReader;
import com.team6.project.readers.UserEquipmentReader;
import com.team6.project.services.DataImportServiceLocal;

@RunWith(Arquillian.class)
public class DataImportServiceTest {

    // TODO: get these from a configuration file instead of hard-coding them in.
    // private final static String TEST_WATCH_PATH =
    // System.getProperty("os.name").startsWith("Windows")?
    // "c:/watching/test/":"/watching/test/";
    private Path testWatchPath;
    private static final String INPUT_FILE_NAME = "DITSampleDataset_SHORT.xls";
    private static final String PATH_TO_TEST_INPUT = "src/test/resources/";

    private static final long DELAY_IN_MS = 500;

    private static boolean importComplete = false;
    
    @EJB
    DataImportServiceLocal service;

    @SuppressWarnings("unchecked")
    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive a = ShrinkWrap
                .create(WebArchive.class, "test.war")
                .addPackages(true, "com.team6.project.services",
                             "com.team6.project.entities",
                             "com.team6.project.readers",
                             "com.team6.project.dao",
                             "com.team6.project.dao.jpa",
                             "com.team6.project.validators")
                .addAsResource("test-persistence.xml",
                               "META-INF/persistence.xml")
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
    public void prepareDataImportTest() throws InterruptedException {
    	if(!importComplete){
    		importDataTest();
    		importComplete = true;
    	}
    }
    
    
    //moved to allowed using this initialization
    public void importDataTest() throws InterruptedException {
        startWatchingFolder();

        // add delay here to ensure the directory is being watched before
        // copying test file into watched directory
        Thread.sleep(DELAY_IN_MS);

        copyTestSheetIntoWatchDirectory();

        // add delay here to allow file to be processed before beginning
        // testing.
        Thread.sleep(DELAY_IN_MS);
    }

    /**
     * This test aims to assert that a file has been picked up by the directory
     * watching thread, and processed into the temporary test database.
     * 
     */
    @Test
    public void testDirectoryWatcher() {
        assertTrue(service.getProcessedFileCount() > 0);
    }

    /**
     * This test aims to assert that the ImportServices internal HashMaps are
     * being populated during data import.
     * 
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testHashMapPopulation() {

        HashMap<EventCausePK, EventCause> eventCauseMap = (HashMap<EventCausePK, EventCause>) service
                .getMap(EventCauseReader.getName());
        assertTrue(eventCauseMap.size() > 0);

        HashMap<Integer, FailureType> failureTypeMap = (HashMap<Integer, FailureType>) service
                .getMap(FailureTypeReader.getName());
        assertTrue(failureTypeMap.size() > 0);

        HashMap<Integer, UserEquipment> userEquipmentMap = (HashMap<Integer, UserEquipment>) service
                .getMap(UserEquipmentReader.getName());
        assertTrue(userEquipmentMap.size() > 0);

        HashMap<OperatorCountryPK, OperatorCountry> operatorCountryMap = (HashMap<OperatorCountryPK, OperatorCountry>) service
                .getMap(OperatorCountryReader.getName());
        assertTrue(operatorCountryMap.size() > 0);
    }

    @After
    public void cleanTestEnvironment() {
//        try {
//            FileUtils.deleteDirectory(testWatchPath.toFile());
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    }

    private void startWatchingFolder() {
        try {
            testWatchPath = Files.createTempDirectory("ArquillanTestDir");
            service.startDirectoryWatcher(testWatchPath.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyTestSheetIntoWatchDirectory() {
        File sourceFile = new File(PATH_TO_TEST_INPUT + INPUT_FILE_NAME);
        File destinationFile = new File(testWatchPath.resolve(INPUT_FILE_NAME)
                .toString());
        try {
            FileUtils.copyFile(sourceFile, destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
