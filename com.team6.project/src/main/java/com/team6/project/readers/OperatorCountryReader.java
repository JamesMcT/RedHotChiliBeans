package com.team6.project.readers;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.OperatorCountryPK;
import com.team6.project.services.DataImportService;
import com.team6.project.services.MapExcelInterface;

/**
 * Reads rows in sheet called MCC - MNC Table. Create the OperatorCountry
 * object. If the object is not already in the appropriated map it is added and
 * written to the DB
 * 
 * @author Cristiana
 */
public class OperatorCountryReader extends Reader {

    private static final String NAME = "MCC - MNC Table";

    public OperatorCountryReader() {
        super();
    }

    @Override
    public void processExcelFile(DataImportService service) {
        HSSFSheet sheet = service.getSheet("MCC - MNC Table");
        while (currentRow <= sheet.getLastRowNum()) {
            HSSFRow row = sheet.getRow(currentRow);
            OperatorCountry operatorCountry = new OperatorCountry();
            operatorCountry.setMcc(getIntegerFromCell(row.getCell(0)));
            operatorCountry.setMnc(getIntegerFromCell(row.getCell(1)));
            operatorCountry.setCountry(getStringFromCell(row.getCell(2)));
            operatorCountry.setOperator(getStringFromCell(row.getCell(3)));
            OperatorCountryPK pk = new OperatorCountryPK(
                                                         operatorCountry
                                                                 .getMcc(),
                                                         operatorCountry
                                                                 .getMnc());
            if (operatorCountry.hasRequiredFields()) {
                if (!service.getMap(NAME).containsKey(pk)) {
                    service.getMap(NAME).put(pk, operatorCountry);
                    // persistence.persist(failure);
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

    }

    public static String getName() {
        return NAME;
    }

}
