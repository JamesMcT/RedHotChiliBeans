package com.team6.project.readers;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.UserEquipment;
import com.team6.project.services.DataImportServiceLocal;

/**
 * Reads rows in sheet called UE Table. Create the UserEquipment object. If the
 * object is not already in the appropriated map it is added and written to the
 * DB
 * 
 * @author Cristiana Conti
 * @author Eoin Kernan
 */
public class UserEquipmentReader extends Reader {

    private static final String NAME = "UE Table";

    /**
     * 
     */
    public UserEquipmentReader() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void processExcelFile(DataImportServiceLocal service) {
    	
        HSSFSheet sheet = service.getSheet(NAME);
        List<UserEquipment> dataList = new ArrayList<UserEquipment>();
        
        while (currentRow <= sheet.getLastRowNum()) {
            HSSFRow row = sheet.getRow(currentRow);
            UserEquipment userEquip = new UserEquipment();
            userEquip.setTac(getIntegerFromCell(row.getCell(0)));
            userEquip.setMarketingName(getStringFromCell(row.getCell(1)));
            userEquip.setManufacturer(getStringFromCell(row.getCell(2)));
            userEquip.setAccessCapability(getStringFromCell(row.getCell(3)));
            userEquip.setModel(getStringFromCell(row.getCell(4)));
            userEquip.setVendorName(getStringFromCell(row.getCell(5)));
            userEquip.setType(getStringFromCell(row.getCell(6)));
            userEquip.setOs(getStringFromCell(row.getCell(7)));
            userEquip.setInputMode(getStringFromCell(row.getCell(8)));
            if (userEquip.hasRequiredFields()) {
                if (!service.getMap(NAME).containsKey(userEquip.getTac())) {
                    readerLogger.info("In sheet " + NAME + " row number "
                            + row.getRowNum() +" not in map. Writing on DB as well....");
                    service.getMap(NAME).put(userEquip.getTac(), userEquip);
                    dataList.add(userEquip);
                    //service.getPersistenceService().persistUserEquipment(userEquip);
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
        	service.getPersistenceService().persistUserEequipmentCollection(dataList);
        }
        currentRow = FIRSTROW;

    }

    /**
     * 
     * @return
     */
    public static String getName() {
        return NAME;
    }

}

