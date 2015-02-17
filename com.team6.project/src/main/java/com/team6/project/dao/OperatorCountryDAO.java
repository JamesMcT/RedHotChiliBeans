package com.team6.project.dao;

import java.util.Collection;

import javax.ejb.Local;

import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.OperatorCountryPK;
@Local
public interface OperatorCountryDAO {
	
	public Collection<OperatorCountry> getAllOperatorCountries();
	
	public OperatorCountry getOperatorCountry(OperatorCountryPK operatorCountryPK);

	public void addNewOperatorCountryDataSet(OperatorCountry operatorCountry);

	public void updateOperatorCountry(OperatorCountryPK operatorCountryPK, OperatorCountry operatorCountry);

	public void deleteOperatorCountry(OperatorCountryPK operatorCountryPK);

}
