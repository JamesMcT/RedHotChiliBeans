package com.team6.project.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;

import static java.nio.file.StandardWatchEventKinds.*;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.team6.project.entities.EventCause;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.OperatorCountry;
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
@Stateless
@Singleton
public class DataImportService implements DataImportServiceLocal, MapExcelInterface{

	//Responsible for interacting with DAO objects and persisting business entities through same.
	@EJB
	private PersistenceService persistenceService;
	
	private List<Reader> readers;
	private HSSFWorkbook workBook;
	private WatchService directoryWatcher;
	
	private final static String WATCH_PATH = "c:/watching/";//TODO: watch a real directory & What will this look like in Linux?
	
	//A map of maps, one map for each cached entity type, using entity name as key.
	private Map<String, HashMap> entityMap = new HashMap<String, HashMap>();
	
	public DataImportService()
	{}

	@PostConstruct
	private void atStartup(){
		
		//create a reader for each sheet in the excel workbook
		addReader(new EventCauseReader());
		addReader(new FailureTypeReader());
		addReader(new OperatorCountryReader());
		addReader(new UserEquipmentReader());
		addReader(new BaseDataReader());
		
		entityMap.put(EventCause.class.getName(), new HashMap<String, EventCause>());
		entityMap.put(FailureType.class.getName(), new HashMap<String, FailureType>());
		entityMap.put(OperatorCountry.class.getName(), new HashMap<String, OperatorCountry>());
		entityMap.put(UserEquipment.class.getName(), new HashMap<String, UserEquipment>());
		
		//Start directory atching service
		startDirectoryWatcher();
	}
	
	/* Given that this class is a singleton, and this method is synchronized, only
	   one file can be processed at any given time. This will stop multiple clients
	   from attempting to upload data simultaneously. Upload/input is not thought 
	   be a very frequent activity, so this restriction should not affect throughput.*/
	public synchronized void processExcelFile(File file){
		for(Reader r:readers){
			r.processExcelFile(this);
		}
	}
	
	private void addReader(Reader r){
		readers.add(r);
	}
	
	private void startDirectoryWatcher(){
		
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

		        // Verify that the new
		        //  file is an Excel file.
		        try {
		            // Resolve the filename against the directory.
		            // If the filename is "test" and the directory is "foo",
		            // the resolved name is "test/foo".
		            Path child = dir.resolve(filename);
		            if (!Files.probeContentType(child).equals("application/vnd.ms-excel") 
		            		&& !Files.probeContentType(child).equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
		                System.err.format("New file '%s'" + " is not a plain text file.%n", filename);
		                continue;
		            }
		        } catch (IOException x) {
		            System.err.println(x);
		            continue;
		        }

		        processExcelFile(new File(filename.toUri()));
		        
		        //TODO
		        //Maybe rename the processed file here? Or move to a sub-directory 'processed' or something.
		    }

		    // Reset the key -- this step is critical if you want to
		    // receive further watch events.  If the key is no longer valid,
		    // the directory is inaccessible so exit the loop.
		    boolean valid = key.reset();
		    if (!valid) {
		        break;
		    }
		}
		
	}

	@Override
	public HSSFSheet getSheet(String sheetName) {
		return workBook.getSheet(sheetName);
	}

	@Override
	public Map getMap(String key) {
		if(entityMap.containsKey(key)){
			return entityMap.get(key);
		}
		return null;
	}
	
}
