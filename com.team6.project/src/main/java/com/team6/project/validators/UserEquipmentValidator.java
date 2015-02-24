package com.team6.project.validators;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.Record;
import com.team6.project.entities.UserEquipment;
import com.team6.project.readers.UserEquipmentReader;
import com.team6.project.services.DataImportServiceLocal;

/**
 * Validates the UserEquipment to guarantee the FK satisfied
 * @author Cristiana 
 */
public class UserEquipmentValidator implements IValidator {

    @Override
    public boolean isValid(Record record, BaseData baseData,
            DataImportServiceLocal service) {
        UserEquipment ue = (UserEquipment) service.getMap(UserEquipmentReader.getName())
                .get(record.getUserEquipment());
        
        if (ue != null) {
            baseData.setUserEquipment(ue);
            return true;
        }
        record.setDescription("Not valid User Equipment");
        return false;
    }

}
