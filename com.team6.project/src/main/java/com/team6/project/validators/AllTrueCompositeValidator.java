package com.team6.project.validators;

import java.util.ArrayList;
import java.util.Collection;

import com.team6.project.dao.jpa.JPABaseDataDAO;
import com.team6.project.entities.BaseData;
import com.team6.project.entities.Record;
import com.team6.project.services.DataImportServiceLocal;

/**
 * Composed by a collection of IValidator object  
 * returns true only if each IValidator returns true.
 * @author Cristiana
 */
public class AllTrueCompositeValidator implements IValidator{

	private Collection<IValidator> validators;
	
	/**
	 * Initializes the IValidators Collection as an empty ArrayList
	 */
	public AllTrueCompositeValidator() {
		validators = new ArrayList<>();
	}
	
	/**
	 * Return false at the first IValidator that returns false.
	 * If all IValidator are true, it builds the BaseData object 
	 * using the Record object
	 * @param Record object that contains raw data
     * @param BaseData object valued with data from record if validations are satisfied
     * @param DataImportServiceLocal contains HashMaps valued with the data of the auxiliary tables 
     * @return true if the validation is satisfied
	 */
	@Override
	public boolean isValid(Record record, BaseData baseData, DataImportServiceLocal service) {
		for(IValidator validator : validators){
			if(!validator.isValid(record, baseData,service)){
				return false;
			}
		}
		JPABaseDataDAO.fillData(record, baseData);
		return true;
	}
	
	/**
	 * Add an IValidator object to a list of IValidator objects
	 * @param validator
	 */
	public void add(IValidator validator){
		validators.add(validator);
	}


	
}
