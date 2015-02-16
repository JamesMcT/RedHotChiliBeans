package com.team6.project.services;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.team6.project.dao.BaseDataDAO;
import com.team6.project.dao.EventCauseDAO;
import com.team6.project.dao.FailureTypeDAO;
import com.team6.project.dao.OperatorCountryDAO;
import com.team6.project.dao.UserEquipmentDAO;
import com.team6.project.entities.EventCause;

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

	public void persisiEventCause(EventCause e){
		//eventCause.getAllEventCauses();
	}
	
}
