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
	
	public AllTrueCompositeValidator() {
		validators = new ArrayList<>();
	}
	/**
	 * Return false at the first IValidator that returns false.
	 * If all IValidator are true, it builds the BaseData object 
	 * using the Record object
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
	
	public void add(IValidator validator){
		validators.add(validator);
	}

	public Collection<IValidator> getValidators() {
		return validators;
	}

	public void setValidators(Collection<IValidator> validators) {
		this.validators = validators;
	}

	
}
