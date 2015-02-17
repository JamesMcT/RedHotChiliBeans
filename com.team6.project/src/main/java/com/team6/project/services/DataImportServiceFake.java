package com.team6.project.services;

import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class DataImportServiceFake implements MapExcelInterface{

    private HSSFWorkbook workBook;
    private Map<String, Map> map;
    
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

    

}
