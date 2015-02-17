package com.team6.project.readers;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.team6.project.entities.UserEquipment;
import com.team6.project.services.MapExcelInterface;
/**
 * Reads rows in sheet called UE Table.
 * Create the UserEquipment object.
 * If the object is not already in the appropriated map
 * it is added and written to the DB
 * 
 * @author Cristiana
 */
public class UserEquipmentReader extends Reader {

    private static final String NAME = "UE Table";

    public UserEquipmentReader() {
        super();
    }

    @Override
    public void processExcelFile(MapExcelInterface service) {
        HSSFSheet sheet = service.getSheet(NAME);
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
                    service.getMap(NAME).put(userEquip.getTac(), userEquip);
                    // persistence.persist(failure);
                }
                // It is already in the map
            } else {
                // Data corrupted write Log file
            }
            currentRow++;
        }

    }

    public static String getName() {
        return NAME;
    }

}
