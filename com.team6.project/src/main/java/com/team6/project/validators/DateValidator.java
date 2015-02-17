package com.team6.project.validators;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.Record;
import com.team6.project.services.MapExcelInterface;

/**
 * Performs a validation over the date field
 * @author Cristiana 
 */
public class DateValidator implements IValidator {

    @Override
    public boolean isValid(Record record, BaseData baseData,
            MapExcelInterface service) {
        if (record.getDate() != null) {
            baseData.setDate(record.getDate());
            return true;
        }
        record.setDescription("Invalid Date");
        return false;
    }

}
