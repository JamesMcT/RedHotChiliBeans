package com.team6.project.validators;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.Record;
import com.team6.project.services.DataImportServiceLocal;


/**
 * IValidator defines the interface of each validator. 
 * @author Cristiana
 */
public interface IValidator {

	/**
	 * The method return true if the checks over the record are satisfied and 
	 * the information is successfully inserted into the BaseData object.
	 * The service is used to retrieve auxiliary information
	 * @param Record object that contains raw data
	 * @param BaseData object valued with data from record if validations are satisfied
	 * @param DataImportServiceLocal contains HashMaps valued with the data of the auxiliary tables 
	 * @return true if the validation is satisfied
	 */
	  public boolean isValid(Record record, BaseData baseData, DataImportServiceLocal service);

}
