package com.team6.project.dao;

import java.util.Collection;

import javax.ejb.Local;

import com.team6.project.entities.FailureType;

/**
 * 
 * @author John O Keeffe
 * @author Eoin Kernan
 *
 */
@Local
public interface FailureTypeDAO {

	/**
	 * 
	 * @return
	 */
	public Collection<FailureType> getAllFailureTypes();

	/**
	 * 
	 * @param failureCode
	 * @return
	 */
	public FailureType getFailureTypeByKey(Integer failureCode);

	/**
	 * 
	 * @param failureType
	 */
	public void addFailureType(FailureType failureType);
	
	/**
	 * 
	 * @param failureType
	 */
	public void addFailureTypeCollection(Collection<FailureType> failureType);

	/**
	 * 
	 * @param failureType
	 */
	public void updateFailureType(FailureType failureType);

//	public void deleteFailureType(Integer failureCode);

	/**
	 * 
	 * @param failureType
	 */
	public void deleteFailureType(FailureType failureType);

	// public void addNewFailureTypeDataSet(FailureType failureType);

	// public FailureType findByFailureCode(Integer failureCode);

	// public void deleteByFailureCode(Integer failureCode);

	// public void deleteAll();

}
