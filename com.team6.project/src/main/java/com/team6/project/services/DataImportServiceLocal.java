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
	 * 
	 */
	public void processExcelFile();
	
	/**
	 * 
	 * @param string
	 * @return
	 */
	public HSSFSheet getSheet(String string);
    
	/**
	 * 
	 * @param string
	 * @return
	 */
    @SuppressWarnings("rawtypes")
    public Map getMap(String string);
    
    /**
     * 
     * @return
     */
    public PersistenceServiceLocal getPersistenceService();
    
    /**
     * 
     * @param folderPath
     */
    public void startDirectoryWatcher(final String folderPath);
    
    /**
     * 
     * @return
     */
    public int getProcessedFileCount();
	
}
