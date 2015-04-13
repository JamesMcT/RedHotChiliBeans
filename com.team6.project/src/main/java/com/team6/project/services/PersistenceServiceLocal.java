package com.team6.project.services;

import java.util.Collection;

import javax.ejb.Local;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.Record;
import com.team6.project.entities.User;
import com.team6.project.entities.UserEquipment;

/**
 * 
 * @author Eoin Kernan
 *
 */
@Local
public interface PersistenceServiceLocal {

	/**
	 * This method returns all the event/causes as a collection
	 * 
	 * @return
	 */
    public Collection<EventCause> getAllEventCauses();
    
    /**
     *  This method returns all the failure types as a collection
     * 
     * @return
     */
    public Collection<FailureType> getAllFailureTypes();
    
    /**
     *  This method returns all the operator/countries as a collection
     * 
     * @return
     */
    public Collection<OperatorCountry> getAllOperatorCountries();
    
    /**
     *  This method returns all the user equipments as a collection
     * 
     * @return
     */
    public Collection<UserEquipment> getAllUserEquipment();
    
    /**
     *  This method returns all the recorded call failures from base data as a collection
     * 
     * @return
     */
    public Collection<BaseData> getAllBaseData();
	
    /**
     *  This method inserts one record to the base data
     * 
     * @param b
     */
    public void persistBaseData(BaseData b);
	
    /**
     * This method inserts a collection of records to the base data
     * 
     * @param b
     */
	public void persistBaseDataCollection(Collection<BaseData> b);
	
	 /**
	  * This method inserts one record the the event cause table
	  * 
	  * @param e
	  */
	public void persistEventCause(EventCause e);
	
	 /**
	  * This method inserts one record to the failure table
	  * 
	  * @param f
	  */
	public void persistFailureType(FailureType f);
	
	 /**
	  * This method inserts one record to the OperatorCountry table
	  * 
	  * @param o
	  */
	public void persistOperatorCountry(OperatorCountry o);
	
	 /**
	  * This method inserts one record to the user equipment table
	  * 
	  * @param u
	  */
	public void persistUserEquipment(UserEquipment u);
	
	 /**
	  * This method inserts one record to the Errouneus table
	  * 
	  * @param r
	  */
	public void persistErroneusRecord(Record r);
	
	 /**
	  * This method inserts a collection of records to the Errouneus table
	  * 
	  * @param r
	  */
	public void persistErroneusRecordCollection(Collection<Record> r);
	
	 /**
	  * This method inserts a collection of records to the eventCause table
	  * 
	  * @param eventCauses
	  */
	public void persistEventCauseCollection(Collection<EventCause> eventCauses);
	
	 /**
	  * This method inserts a collection of records to the failureTypes table
	  * 
	  * @param failureTypes
	  */
	public void persistFailureTypeCollection(Collection<FailureType> failureTypes);
	
	 /**
	  * This method inserts a collection of records to the OperatorCountry table
	  * 
	  * @param operatorCountries
	  */
	public void persistOperatorCountryCollection(Collection<OperatorCountry> operatorCountries);
	
	 /**
	  * This method inserts a collection of records to the userEquipment table
	  * 
	  * @param userEquipment
	  */
	public void persistUserEquipmentCollection(Collection<UserEquipment> userEquipment);

	
	/**
	 * This method inserts a record to the users table
	 * 
	 * @param newUser
	 */
	public void addUser(User newUser);
	
	/**
	 * This method updates a record in the users table
	 * 
	 * @param newUser
	 */	
	public void updateUser(User newUser);

}

