package com.team6.project.services;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.team6.project.dao.BaseDataDAO;
import com.team6.project.dao.EventCauseDAO;
import com.team6.project.dao.FailureTypeDAO;
import com.team6.project.dao.OperatorCountryDAO;
import com.team6.project.dao.UserEquipmentDAO;
import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.UserEquipment;

/**
 * The persistence service. This class is reponsible for taking in a set of arguments,
 * then creating the requisite Entity object and calling an underlying DAO to persist
 * the object in the back end.
 * 
 * @author Eoin Kernan
 *
 */
@Local
@Stateless
public class PersistenceService {

	@Inject
	private BaseDataDAO baseData;
	
	@Inject
	private EventCauseDAO eventCause;
	
	@Inject
	private FailureTypeDAO failureType;
	
	@Inject
	private OperatorCountryDAO operatorCountry;
	
	@Inject
	private UserEquipmentDAO userEquipment;
	
	public PersistenceService(){}

	public Collection<BaseData> getAllBaseData(){
		return baseData.getAllBaseData();
	}
	
	public Collection<EventCause> getAllEventCauses(){
		return eventCause.getAllCauses();
	}
	
	public Collection<FailureType> getAllFailureTypes(){
		return failureType.getAllFailureTypes();
	}
	
	public Collection<OperatorCountry> getAllOperatorCountries(){
		return operatorCountry.getAllOperatorCountries();
	}
	
	public Collection<UserEquipment> getAllUserEquipment(){
		return userEquipment.getAllUserEquipment();
	}
	
	public void persistBaseData(BaseData b){
		baseData.addNewBaseDataSet(b);
	}
	
	public void persistEventCause(EventCause e){
		eventCause.addNewEventCauseDataSet(e);
	}
	
	public void persistFailureType(FailureType f){
		failureType.addFailureType(f);
	}
	
	public void persistOperatorCountry(OperatorCountry o){
		operatorCountry.addNewOperatorCountryDataSet(o);
	}
	
	public void persistUserEquipment(UserEquipment u){
		userEquipment.addNewUserEquipmentDataSet(u);
	}
	
}
