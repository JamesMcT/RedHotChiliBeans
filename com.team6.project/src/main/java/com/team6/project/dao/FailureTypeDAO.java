package com.team6.project.dao;

import java.util.Collection;

import com.team6.project.entities.FailureType;


public interface FailureTypeDAO {

	public Collection<FailureType> getFailureTypes();

	public void addNewFailureTypeDataSet(FailureType failureType);

	public void updateFailureType(FailureType failureType);

	public FailureType findByFailureCode(Integer failureCode);

	public void deleteByFailureCode(Integer failureCode);

	public void deleteAll();
	
}
