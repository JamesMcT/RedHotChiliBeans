package com.team6.project.services;

import java.util.Map;

import javax.enterprise.inject.Alternative;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 
 * @author 
 *
 */
@Alternative
public class DataImportServiceFake implements DataImportServiceLocal{

    private HSSFWorkbook workBook;
    @SuppressWarnings("rawtypes")
    private Map<String, Map> map;
    
    PersistenceServiceFake persistenceServiceFake;
    
    /**
     * 
     */
    public DataImportServiceFake() {
        super();
    }

    @Override
    public HSSFSheet getSheet(String string) {
        return workBook.getSheet(string);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Map getMap(String string) {
        return map.get(string);
    }

    /**
     * 
     * @return
     */
    public HSSFWorkbook getWorkBook() {
        return workBook;
    }

    /**
     * 
     * @param workBook
     */
    public void setWorkBook(HSSFWorkbook workBook) {
        this.workBook = workBook;
    }

    /**
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    public Map<String, Map> getMap() {
        return map;
    }

    /**
     * 
     * @param map
     */
    public void setMap(@SuppressWarnings("rawtypes") Map<String, Map> map) {
        this.map = map;
    }

    /**
     * 
     */
    @Override
    public void processExcelFile() {
        // TODO Auto-generated method stub
        
    }

    /**
     * 
     */
    @Override
    public PersistenceServiceLocal getPersistenceService() {
        return new PersistenceServiceFake();
    }

    /**
     * 
     */
	@Override
	public void startDirectoryWatcher(String folderPath) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	@Override
	public int getProcessedFileCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
