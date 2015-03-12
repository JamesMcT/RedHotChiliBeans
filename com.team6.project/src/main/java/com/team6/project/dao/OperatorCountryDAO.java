package com.team6.project.dao;

import java.util.Collection;

import javax.ejb.Local;

import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.OperatorCountryPK;

/**
 * 
 * @author John O Keeffe
 * @author Eoin Kernan
 *
 */
@Local
public interface OperatorCountryDAO {

	/**
	 * 
	 * @return all operator countries
	 */
	public Collection<OperatorCountry> getAllOperatorCountries();

	/**
	 * 
	 * @param operatorCountryPK
	 * @return
	 */
	public OperatorCountry getOperatorCountryByKey(
			OperatorCountryPK operatorCountryPK);

	public void addOperatorCountry(OperatorCountry operatorCountry);
	
	/**
	 * 
	 * @param operatorCountry
	 */
	public void addOperatorCountryCollection(Collection<OperatorCountry> operatorCountry);

	/**
	 * Update existing OperatorCountry, record to be updated returned via PK.
	 * Updated OperatorCountry also passed.
	 * 
	 * @param operatorCountry
	 */
	public void updateOperatorCountry(OperatorCountry operatorCountry);

	/**
	 * Delete operatorCountry record by composite key (OperatorCountryPK)
	 * 
	 * @param operatorCountry
	 */
	public void deleteOperatorCountry(OperatorCountry operatorCountry);

	// public OperatorCountry getByOperatorCountry(Integer mcc, Integer mnc);

}
