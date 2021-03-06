package com.team6.project.readers;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.OperatorCountryPK;
import com.team6.project.services.DataImportServiceLocal;

/**
 * Reads rows in sheet called MCC - MNC Table. Create the OperatorCountry
 * object. If the object is not already in the appropriated map it is added and
 * written to the DB
 * 
 * @author Cristiana Conti
 * @author Eoin Kernan
 */
public class OperatorCountryReader extends Reader {

    private static final String NAME = "MCC - MNC Table";

    /**
     * 
     */
    public OperatorCountryReader() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void processExcelFile(DataImportServiceLocal service) {
    	
        HSSFSheet sheet = service.getSheet(NAME);
        List<OperatorCountry> dataList = new ArrayList<OperatorCountry>();
        
        while (currentRow <= sheet.getLastRowNum()) {
            HSSFRow row = sheet.getRow(currentRow);
            OperatorCountry operatorCountry = new OperatorCountry();
            operatorCountry.setMcc(getIntegerFromCell(row.getCell(0)));
            operatorCountry.setMnc(getIntegerFromCell(row.getCell(1)));
            operatorCountry.setCountry(getStringFromCell(row.getCell(2)));
            operatorCountry.setOperator(getStringFromCell(row.getCell(3)));
            OperatorCountryPK pk = operatorCountry.getKey();
            readerLogger.info(service.getMap(NAME));

            if (operatorCountry.hasRequiredFields()) {
                if (!service.getMap(NAME).containsKey(pk)) {
                    readerLogger.info("In sheet " + NAME + " row number "
                            + row.getRowNum() +" not in map. Writing on DB as well....");
                    service.getMap(NAME).put(pk, operatorCountry);
                    dataList.add(operatorCountry);
                    //service.getPersistenceService().persistOperatorCountry(operatorCountry);
                } else {
                    readerLogger.info("In sheet " + NAME + " row number "
                            + row.getRowNum() + " already in memory");
                }
            } else {
                readerLogger.warn("In sheet " + NAME + " row number "
                        + row.getRowNum() + " primary key not valued properly");
            }
            currentRow++;
        }
        
        if(dataList.size()>0){
        	service.getPersistenceService().persistOperatorCountryCollection(dataList);
        }
        currentRow = FIRSTROW;

    }

    /**
     * returns the name of the excel sheet
     * @return
     */
    public static String getName() {
        return NAME;
    }
}
