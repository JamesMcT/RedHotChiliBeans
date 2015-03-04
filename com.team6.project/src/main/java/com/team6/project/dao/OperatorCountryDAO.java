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

	public Collection<OperatorCountry> getAllOperatorCountries();

	public OperatorCountry getOperatorCountryByKey(
			OperatorCountryPK operatorCountryPK);

	public void addOperatorCountry(OperatorCountry operatorCountry);
	
	public void addOperatorCountryCollection(Collection<OperatorCountry> operatorCountry);

	public void updateOperatorCountry(OperatorCountry operatorCountry);

	public void deleteOperatorCountry(OperatorCountry operatorCountry);

	// public OperatorCountry getByOperatorCountry(Integer mcc, Integer mnc);

}
