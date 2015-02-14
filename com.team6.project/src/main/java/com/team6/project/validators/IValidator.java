package com.team6.project.validators;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.Record;
import com.team6.project.services.MapExcelInterface;

/**
 * Interface implemented by all validators
 * @author Cristiana
 */
public interface IValidator {

	  public boolean isValid(Record record, BaseData baseData, MapExcelInterface service);

}
