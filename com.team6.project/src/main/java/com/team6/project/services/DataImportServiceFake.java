package com.team6.project.services;

import java.util.Map;

import javax.enterprise.inject.Alternative;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

@Alternative
public class DataImportServiceFake implements DataImportServiceLocal{

    private HSSFWorkbook workBook;
    private Map<String, Map> map;
    
    PersistenceServiceFake persistenceServiceFake;
    
    public DataImportServiceFake() {
        super();
    }

    @Override
    public HSSFSheet getSheet(String string) {
        return workBook.getSheet(string);
    }

    @Override
    public Map getMap(String string) {
        return map.get(string);
    }

    public HSSFWorkbook getWorkBook() {
        return workBook;
    }

    public void setWorkBook(HSSFWorkbook workBook) {
        this.workBook = workBook;
    }

    public Map<String, Map> getMap() {
        return map;
    }

    public void setMap(Map<String, Map> map) {
        this.map = map;
    }

    @Override
    public void processExcelFile() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public PersistenceServiceLocal getPersistenceService() {
        return new PersistenceServiceFake();
    }

	@Override
	public void startDirectoryWatcher(String folderPath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFileCount() {
		// TODO Auto-generated method stub
		return 0;
	}

    

}
