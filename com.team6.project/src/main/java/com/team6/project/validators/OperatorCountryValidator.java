package com.team6.project.validators;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.OperatorCountryPK;
import com.team6.project.entities.Record;
import com.team6.project.readers.OperatorCountryReader;
import com.team6.project.services.DataImportServiceLocal;

/**
 * Validates the MCC and the MNC to guarantee the FK satisfied
 * If the constraint is satisfied the BaseData OperatorCountry property is set with 
 * the same value of the Record OperatorCountry property. Otherwise a
 * description of the error is set into the description property of the Record object.
 * @author Cristiana
 */
 
public class OperatorCountryValidator implements IValidator {

    @Override
    public boolean isValid(Record record, BaseData baseData,
            DataImportServiceLocal service) {
        if (record.getMcc() != null && record.getMnc() != null) {
            OperatorCountryPK pk = new OperatorCountryPK(record.getMcc(),
                                                         record.getMnc());
            OperatorCountry oc = (OperatorCountry) service
                    .getMap(OperatorCountryReader.getName()).get(pk);
            if (oc != null) {
                baseData.setOperatorCountry(oc);
                return true;
            }
        }
        record.setDescription("Not valid MCC / MNC");
        return false;
    }
}
