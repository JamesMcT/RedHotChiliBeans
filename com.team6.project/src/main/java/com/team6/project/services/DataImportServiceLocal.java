package com.team6.project.services;

import java.util.Map;

import javax.ejb.Local;

import org.apache.poi.hssf.usermodel.HSSFSheet;

/**
 * 
 * @author administrator
 *
 */
@Local
public interface DataImportServiceLocal {
	
	/**
	 * This method processing the excel file
	 */
	public void processExcelFile();
	
	/**
	 * This method returns the excel sheet by the given parameter
	 * 
	 * @param string
	 * @return
	 */
	public HSSFSheet getSheet(String string);
    
	/**
	 * This method retrurns the entity relations map for the database
	 * 
	 * @param string
	 * @return
	 */
    @SuppressWarnings("rawtypes")
    public Map getMap(String string);
    
    /**
     * This method returns back the persistence service EJB reference
     * 
     * @return
     */
    public PersistenceServiceLocal getPersistenceService();
    
    /**
     * This method starts watching the folder for any changes
     * 
     * @param folderPath
     */
    public void startDirectoryWatcher(final String folderPath);
    
    /**
     * This method returns of how many files have been processed
     * 
     * @return
     */
    public int getProcessedFileCount();
	
}
