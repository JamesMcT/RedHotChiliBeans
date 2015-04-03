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
	 * 
	 * @return
	 */
    public Collection<EventCause> getAllEventCauses();
    
    /**
     * 
     * @return
     */
    public Collection<FailureType> getAllFailureTypes();
    
    /**
     * 
     * @return
     */
    public Collection<OperatorCountry> getAllOperatorCountries();
    
    /**
     * 
     * @return
     */
    public Collection<UserEquipment> getAllUserEquipment();
    
    /**
     * 
     * @return
     */
    public Collection<BaseData> getAllBaseData();
	
    /**
     * 
     * @return
     */
    public void persistBaseData(BaseData b);
	
    /**
     * 
     * @return
     */
	public void persistBaseDataCollection(Collection<BaseData> b);
	
	 /**
     * 
     * @return
     */
	public void persistEventCause(EventCause e);
	
	 /**
     * 
     * @return
     */
	public void persistFailureType(FailureType f);
	
	 /**
     * 
     * @return
     */
	public void persistOperatorCountry(OperatorCountry o);
	
	 /**
     * 
     * @return
     */
	public void persistUserEquipment(UserEquipment u);
	
	 /**
     * 
     * @return
     */
	public void persistErroneusRecord(Record r);
	
	 /**
     * 
     * @return
     */
	public void persistErroneusRecordCollection(Collection<Record> r);
	
	 /**
     * 
     * @return
     */
	public void persistEventCauseCollection(Collection<EventCause> eventCauses);
	
	 /**
     * 
     * @return
     */
	public void persistFailureTypeCollection(Collection<FailureType> failureTypes);
	
	 /**
     * 
     * @return
     */
	public void persistOperatorCountryCollection(Collection<OperatorCountry> operatorCountries);
	
	 /**
     * 
     * @return
     */
	public void persistUserEquipmentCollection(Collection<UserEquipment> userEquipment);

	public void addUser(User newUser);
	
	public void updateUser(User newUser);

}

