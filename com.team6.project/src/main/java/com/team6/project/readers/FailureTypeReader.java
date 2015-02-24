package com.team6.project.readers;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.team6.project.entities.FailureType;
import com.team6.project.services.DataImportServiceLocal;

/**
 * Reads rows in sheet called Failure Class Table. Create the FailureType
 * object. If the object is not already in the appropriated map it is added and
 * written to the DB
 * 
 * @author Cristiana
 */
public class FailureTypeReader extends Reader {

    private static final String NAME = "Failure Class Table";

    public FailureTypeReader() {
        super();
    }

    @SuppressWarnings("unchecked")
    public void processExcelFile(DataImportServiceLocal service) {
        HSSFSheet sheet = service.getSheet(NAME);
        while (currentRow <= sheet.getLastRowNum()) {
            HSSFRow row = sheet.getRow(currentRow);
            FailureType failure = new FailureType();
            failure.setFailureCode(getIntegerFromCell(row.getCell(0)));
            failure.setDescription(getStringFromCell(row.getCell(1)));
            if (failure.hasRequiredFields()) {
                if (!service.getMap(NAME).containsKey(failure.getFailureCode())) {
                    readerLogger.info("In sheet " + NAME + " row number "
                            + row.getRowNum() +" not in map. Writing on DB as well....");
                    service.getMap(NAME).put(failure.getFailureCode(), failure);
                    service.getPersistenceService().persistFailureType(failure);;
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
        currentRow = FIRSTROW;

    }

    public static String getName() {
        return NAME;
    }

}
