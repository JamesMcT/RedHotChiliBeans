package com.team6.project.validators;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.Record;
import com.team6.project.readers.FailureTypeReader;
import com.team6.project.services.DataImportService;
import com.team6.project.services.MapExcelInterface;

/**
 * Validates the FailureType to guarantee the FK satisfied
 * 
 * @author Cristiana
 */
public class FailureTypeValidator implements IValidator {

    @Override
    public boolean isValid(Record record, BaseData baseData,
    		DataImportService service) {
        FailureType failure = (FailureType) service.getMap(FailureTypeReader
                                                                   .getName())
                .get(record.getFailureType());
        if (failure != null) {
            baseData.setFailure(failure);
            return true;
        }
        record.setDescription("Not valid Failure Id");
        return false;
    }

}
