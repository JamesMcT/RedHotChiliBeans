package com.team6.project.dao;

import java.util.Collection;

import javax.ejb.Local;

import com.team6.project.entities.FailureType;

/**
 * Failure Type Data Access Object
 * <p>
 * This Failure Type data access object will provide abstract methods to
 * implement basic CRUD operations on EventCause objects.
 * 
 * @author John O Keeffe
 * @author Eoin Kernan
 * @author James Mc Ternan
 *
 */
@Local
public interface FailureTypeDAO {

	/**
	 * Get all failureTypes.
	 * 
	 * @return Collection<FailureType>
	 */
	public Collection<FailureType> getAllFailureTypes();

	/**
	 * Get FailureType By failure code.
	 * 
	 * @param failureCode
	 * @return FailureType
	 */
	public FailureType getFailureTypeByKey(Integer failureCode);

	/**
	 * Add FailureType.
	 * 
	 * @param failureType
	 */
	public void addFailureType(FailureType failureType);

	/**
	 * Add multiple failure types
	 * 
	 * @param failureType
	 */
	public void addFailureTypeCollection(Collection<FailureType> failureType);

	/**
	 * Update existing failure type.
	 * 
	 * @param failureType
	 */
	public void updateFailureType(FailureType failureType);

	/**
	 * Delete existing failure type. Removes passed failureType object.
	 * 
	 * @param failureType
	 */
	public void deleteFailureType(FailureType failureType);

}
