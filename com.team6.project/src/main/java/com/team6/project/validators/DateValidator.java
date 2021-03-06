package com.team6.project.validators;

import java.util.Date;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.Record;
import com.team6.project.services.DataImportServiceLocal;

/**
 * Performs a validation over the date property of the Record object. If the date is a valid date 
 * and it is not in the future, the BaseData date property is set with the same value. Otherwise a
 * description of the error is set into the description property of the Record object.
 * @author Cristiana 
 */
public class DateValidator implements IValidator {


    @Override
    public boolean isValid(Record record, BaseData baseData,
            DataImportServiceLocal service) {
        if (record.getDate() != null && (record.getDate().getTime()) <= new Date().getTime()) {
            baseData.setDate(record.getDate());
            return true;
        }
        record.setDescription("Invalid Date");
        return false;
    }

}
