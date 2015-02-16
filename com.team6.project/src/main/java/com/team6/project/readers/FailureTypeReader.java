package com.team6.project.readers;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.team6.project.entities.FailureType;
import com.team6.project.services.MapExcelInterface;

/**
 * Reads rows in sheet called Failure Class Table.
 * Create the FailureType object.
 * If the object is not already in the appropriated map
 * it is added and written to the DB
 * 
 * @author Cristiana
 */
public class FailureTypeReader extends Reader {

    private static final String NAME = "Failure Class Table";

    public FailureTypeReader() {
        super();
    }

    public void processExcelFile(MapExcelInterface service) {
        HSSFSheet sheet = service.getSheet(NAME);
        while (currentRow <= sheet.getLastRowNum()) {
            HSSFRow row = sheet.getRow(currentRow);
            FailureType failure = new FailureType();
            failure.setFailureCode(getIntegerFromCell(row.getCell(0)));
            failure.setDescription(getStringFromCell(row.getCell(1)));
            if (failure.hasRequiredFields()) {
                if (!service.getMap(NAME).containsKey(failure.getFailureCode())) {
                    service.getMap(NAME).put(failure.getFailureCode(), failure);
                    // persistence.persist(failure);
                }
                else{
                }
            } else {
            }
            currentRow++;
        }

    }

    public static String getName() {
        return NAME;
    }

}
