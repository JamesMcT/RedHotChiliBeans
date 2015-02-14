package com.team6.project.services;

import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;

public interface MapExcelInterface {

    public HSSFSheet getSheet(String string);
    
    public Map getMap(String string);
    
}
