package com.team6.project.validators;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.Record;
import com.team6.project.readers.FailureTypeReader;
import com.team6.project.services.DataImportServiceLocal;

/**
 * Validates the FailureType to guarantee the FK satisfied
 * If the constraint is satisfied the BaseData FailureType property is set with 
 * the same value of the Record FailureType property. Otherwise a
 * description of the error is set into the description property of the Record object.
 * @author Cristiana
 */

public class FailureTypeValidator implements IValidator {

    @Override
    public boolean isValid(Record record, BaseData baseData,
            DataImportServiceLocal service) {
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
