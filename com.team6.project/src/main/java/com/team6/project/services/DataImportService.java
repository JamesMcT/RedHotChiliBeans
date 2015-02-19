package com.team6.project.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Default;

import static java.nio.file.StandardWatchEventKinds.*;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.team6.project.entities.EventCause;
import com.team6.project.entities.EventCausePK;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.OperatorCountryPK;
import com.team6.project.entities.UserEquipment;
import com.team6.project.readers.BaseDataReader;
import com.team6.project.readers.EventCauseReader;
import com.team6.project.readers.FailureTypeReader;
import com.team6.project.readers.OperatorCountryReader;
import com.team6.project.readers.Reader;
import com.team6.project.readers.UserEquipmentReader;


/**
 * The data import service. This class is responsible for watching a folder on the server
 * for new files. It will also take delivery of a file from a client directly (e.g. a rest
 * based service). This class will, at startup, begin watching a specified directory for
 * new files. 
 * 
 * @author Eoin Kernan
 *
 */
@Local
@Startup
//@Stateless
@Default
@Singleton
public class DataImportService implements DataImportServiceLocal{

	//Responsible for interacting with DAO objects and persisting business entities through same.
	@EJB
	private PersistenceServiceLocal persistenceService;
	
	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DataImportService.class);
	
	private List<Reader> readers = new ArrayList<Reader>();
	private HSSFWorkbook workBook;
	private WatchService directoryWatcher;
	
	//choose which path to folder to watch, based on underlying OS. If not Windows, then Linux is assumed.
	private final static String WATCH_PATH =
	System.getProperty("os.name").startsWith("Windows")? "c:/watching/":"/watching/";
	
	private final static String PROCESSED_FILE_SUFFIX = ".processed";
	
	//A map of maps, one map for each cached entity type, using entity name as key.
	@SuppressWarnings("rawtypes")
    private Map<String, HashMap> entityMap = new HashMap<String, HashMap>();
	
	public DataImportService()
	{}
	
	@PostConstruct
	private void atStartup(){
		
		logger.info("DataImportService.atStartUp()...");
		
		initialiseHashMaps();
		initialiseReaders();
		startDirectoryWatcher();
	}
	
	private void initialiseHashMaps(){
		entityMap.put(EventCauseReader.getName(), new HashMap<EventCausePK, EventCause>());
		entityMap.put(FailureTypeReader.getName(), new HashMap<Integer, FailureType>());
		entityMap.put(UserEquipmentReader.getName(), new HashMap<Integer, UserEquipment>());
		entityMap.put(OperatorCountryReader.getName(), new HashMap<OperatorCountryPK, OperatorCountry>());
		
		logger.info("Filling map for EventCause...");
		for(EventCause e : persistenceService.getAllEventCauses()){
			entityMap.get(EventCauseReader.getName()).put(e.getKey(), e);
		}
		logger.info("Filling map for FailureType...");
		for(FailureType f : persistenceService.getAllFailureTypes()){
			entityMap.get(FailureTypeReader.getName()).put(f.getKey(), f);
		}
		logger.info("Filling map for OperatorCountry...");
		for(OperatorCountry o: persistenceService.getAllOperatorCountries()){
			entityMap.get(OperatorCountryReader.getName()).put(o.getKey(), o);
		}
		logger.info("Filling map for UserEquipment...");
		for(UserEquipment u:persistenceService.getAllUserEquipment()){
			entityMap.get(UserEquipmentReader.getName()).put(u.getKey(), u);
		}
	}
	
	private void initialiseReaders(){
		//create a reader for each sheet in the excel workbook
		addReader(new EventCauseReader());
		addReader(new FailureTypeReader());
		addReader(new OperatorCountryReader());
		addReader(new UserEquipmentReader());
		addReader(new BaseDataReader());
		
		logger.info("Readers Intialized...");
	}
	
	/* Given that this class is a singleton, and this method is synchronized, only
	   one file can be processed at any given time. This will stop multiple clients
	   from attempting to upload data simultaneously. Upload/input is not thought 
	   be a very frequent activity, so this restriction should not affect throughput.*/
	public synchronized void processExcelFile(){
		
		logger.info("DataImportService.processExcelFile()...");
		
		for(Reader r:readers){
			r.processExcelFile(this);
		}
	}
	
	public PersistenceServiceLocal getPersistenceService(){
		return persistenceService;
	}
	
	private void addReader(Reader r){
		readers.add(r);
	}
	
	private void startDirectoryWatcher(){
		
		logger.info("DataImportService.startDirectoryWatcher()...");
		
		try 
		{directoryWatcher = FileSystems.getDefault().newWatchService();} 
		catch (IOException e) 
		{e.printStackTrace();}
		
		new Thread(){
			public void run(){
				watchDirectory();
			}
		}.start();
	}
	
	private void watchDirectory(){
		
		//Code pretty much lifted straight from "http://docs.oracle.com/javase/tutorial/essential/io/notification.html"
		
		Path dir = Paths.get(WATCH_PATH);
		try
		{WatchKey key = dir.register(directoryWatcher,ENTRY_CREATE);}//watch for new files only
		catch(IOException x) 
		{System.err.println(x);}
		
		for (;;) {

			logger.info("DataImportService beginning watchDirectory()...");

		    // wait for key to be signaled
		    WatchKey key;
		    try {
		        key = directoryWatcher.take();
		    } catch (InterruptedException x) {
		        return;
		    }

		    for (WatchEvent<?> event: key.pollEvents()) {
		        WatchEvent.Kind<?> kind = event.kind();
		        
		        // This key is registered only
		        // for ENTRY_CREATE events,
		        // but an OVERFLOW event can
		        // occur regardless if events
		        // are lost or discarded.
		        if (kind == OVERFLOW) {
		            continue;
		        }

		        // The filename is the
		        // context of the event.
		        WatchEvent<Path> ev = (WatchEvent<Path>)event;
		        Path filename = ev.context();

		        String realURI = WATCH_PATH + filename;
		        
		        // Do not process files that have been marked as processed.
		        if(realURI.contains(PROCESSED_FILE_SUFFIX)){
		        	continue;
		        }
		        
		        // Verify that the new file is an Excel file.
		        try {
		            // Resolve the filename against the directory.
		            // If the filename is "test" and the directory is "foo",
		            // the resolved name is "test/foo".
		            Path child = dir.resolve(filename);
		            if (!Files.probeContentType(child).equals("application/vnd.ms-excel") 
		            		&& !Files.probeContentType(child).equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
		                System.err.format("New file '%s'" + " is not a spreadsheet file.%n", filename);
		                continue;
		            }
		        } catch (IOException x) {
		            System.err.println(x);
		            continue;
		        }

		        try {
					workBook = new HSSFWorkbook(new FileInputStream(realURI));
					
					// Process the input file, time  the activity.
					long beginTime = System.currentTimeMillis();
					processExcelFile();
					long endTime = System.currentTimeMillis();
					
					//write input processing time to log file
					double timeTaken = ((double)(endTime - beginTime))/1000;
					logger.info("DataImportService: Input processing complete in "+new DecimalFormat("0.00").format(timeTaken)+"s");
					
					//re-name file
					workBook.close();
					renameFileAfterProcessing(realURI);
					
				} catch (IOException e) {
					//File exception...maybe file is locked. Watcher will continue watching the folder
					e.printStackTrace();
					logger.info("DataImportService watchDirectory() exception: "+e.getMessage());
				}

		    }

		    // Reset the key -- this step is critical if you want to
		    // receive further watch events.  If the key is no longer valid,
		    // the directory is inaccessible so exit the loop.
		    boolean valid = key.reset();
		    if (!valid) {
		        break;
		    }
		    
		    logger.info("DataImportService ending watchDirectory()...");
		}
		
	}

	private void renameFileAfterProcessing(String fullName){
		
		String proposedName = fullName + PROCESSED_FILE_SUFFIX;
		String finalName = proposedName;
		
		File file = new File(fullName);
		File file2 = new File(proposedName);
		
		//If new name exists, keep appending an (int) until you reach one that doesn't exist yet.
		int failCount = 1;
		while(file2.exists()){
			finalName = proposedName + "("+ (failCount++) +")";
			file2 = new File(finalName);
		}
		
		if(file.renameTo(file2)){
			logger.info(String.format("DataImportService: Successfully renamed file '%s' to '%s'",fullName, finalName));
		}
		else{
			logger.info(String.format("DataImportService: Error renaming '%s' to '%s'",fullName, finalName));
		}
	}
	
	public HSSFSheet getSheet(String sheetName) {
		return workBook.getSheet(sheetName);
	}

	@SuppressWarnings("rawtypes")
    public Map getMap(String key) {
		if(entityMap.containsKey(key)){
			return entityMap.get(key);
		}
		return null;
	}
	
}
