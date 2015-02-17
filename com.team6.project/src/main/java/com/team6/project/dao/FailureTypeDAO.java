package com.team6.project.dao;

import java.util.Collection;

import javax.ejb.Local;

import com.team6.project.entities.FailureType;

@Local
public interface FailureTypeDAO {

	public Collection<FailureType> getAllFailureTypes();

	public FailureType getFailureTypeByFailureCode(Integer failureCode);

	public void addFailureType(FailureType failureType);

	public void updateFailureType(FailureType failureType);

	public void deleteFailureTypeByFailureCode(Integer failureCode);

	public void deleteFailureType(FailureType failureType);

	// public void addNewFailureTypeDataSet(FailureType failureType);

	// public FailureType findByFailureCode(Integer failureCode);

	// public void deleteByFailureCode(Integer failureCode);

	// public void deleteAll();

}
