package com.team6.project.dao;

import java.util.Collection;

import javax.ejb.Local;

import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.OperatorCountryPK;

/**
 * Operator Country data access object.
 * <p>
 * Handles database interactions for OperatorCountry related functionality. This
 * is primarily CRUD operations as this object behaves as a reference table.
 * 
 * @author John O Keeffe
 * @author Eoin Kernan
 * @author James Mc Ternan
 *
 */
@Local
public interface OperatorCountryDAO {

	/**
	 * Get all operator Countries.
	 * 
	 * @return all operator countries
	 */
	public Collection<OperatorCountry> getAllOperatorCountries();

	/**
	 * Get operatorCountry by operatorCountryPK (Primary Key).
	 * 
	 * @param operatorCountryPK
	 * @return OperatorCountry
	 */
	public OperatorCountry getOperatorCountryByKey(
			OperatorCountryPK operatorCountryPK);

	/**
	 * Add single OperatorCountry.
	 * 
	 * @param operatorCountry
	 */
	public void addOperatorCountry(OperatorCountry operatorCountry);

	/**
	 * Add multiple operatorCountry objects.
	 * 
	 * @param operatorCountry
	 */
	public void addOperatorCountryCollection(
			Collection<OperatorCountry> operatorCountry);

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

}
