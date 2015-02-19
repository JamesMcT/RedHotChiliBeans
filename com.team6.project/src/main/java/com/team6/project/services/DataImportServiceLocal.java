package com.team6.project.services;

import java.util.Map;

import javax.ejb.Local;

import org.apache.poi.hssf.usermodel.HSSFSheet;

@Local
public interface DataImportServiceLocal {
	public void processExcelFile();
	
	public HSSFSheet getSheet(String string);
    
    public Map getMap(String string);
    
    public PersistenceServiceLocal getPersistenceService();
    
    public void startDirectoryWatcher(final String folderPath);
    
    public int getFileCount();
	
}
