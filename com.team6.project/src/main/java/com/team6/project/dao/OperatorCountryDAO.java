package com.team6.project.dao;


import java.util.Collection;

import com.team6.project.entities.OperatorCountry;

public interface OperatorCountryDAO {

	public Collection<OperatorCountry> getOperatorCountryRecords();

	public void addNewOperatorCountryDataSet(OperatorCountry operatorCountry);

	public void updateOperatorCountry(OperatorCountry operatorCountry);

	public OperatorCountry findByOperatorCountry(Integer mcc, Integer mnc);

	public void deleteByMccAndMnc(Integer mcc, Integer mnc);

	public void deleteAll();
	
}
