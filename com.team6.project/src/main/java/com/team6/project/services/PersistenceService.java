package com.team6.project.services;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import com.team6.project.dao.BaseDataDAO;
import com.team6.project.dao.EventCauseDAO;
import com.team6.project.dao.FailureTypeDAO;
import com.team6.project.dao.OperatorCountryDAO;
import com.team6.project.dao.RecordDAO;
import com.team6.project.dao.UserDAO;
import com.team6.project.dao.UserEquipmentDAO;
import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.Record;
import com.team6.project.entities.Response;
import com.team6.project.entities.User;
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
@Default
public class PersistenceService implements PersistenceServiceLocal{

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
	
	@Inject
	private RecordDAO record;
	
	@Inject
    private UserDAO user;
	
	public PersistenceService(){}

	@Override
	public Collection<BaseData> getAllBaseData(){
		return baseData.getAllBaseData();
	}
	
	@Override
	public Collection<EventCause> getAllEventCauses(){
		return eventCause.getAllEventCauses();
	}
	
	@Override
	public Collection<FailureType> getAllFailureTypes(){
		return failureType.getAllFailureTypes();
	}
	
	@Override
	public Collection<OperatorCountry> getAllOperatorCountries(){
		return operatorCountry.getAllOperatorCountries();
	}
	
	@Override
	public Collection<UserEquipment> getAllUserEquipment(){
		return userEquipment.getAllUserEquipment();
	}
	
	@Override
	public void persistBaseData(BaseData b){
		baseData.addBaseData(b);
	}
	
	@Override
	public void persistBaseDataCollection(Collection<BaseData> b){
		baseData.addBaseDataCollection(b);
	}
	
	@Override
	public void persistEventCause(EventCause e){
		eventCause.addEventCauseData(e);
	}
	
	@Override
	public void persistFailureType(FailureType f){
		failureType.addFailureType(f);
	}
	
	@Override
	public void persistOperatorCountry(OperatorCountry o){
		operatorCountry.addOperatorCountry(o);
	}
	
	@Override
	public void persistUserEquipment(UserEquipment u){
		userEquipment.addUserEquipment(u);
	}

	@Override
	public void persistErroneusRecord(Record r) {
		record.addRecord(r);

	}
	
	@Override
	public void persistErroneusRecordCollection(Collection<Record> r){
		record.addRecordCollection(r);
	}

	@Override
	public void persistEventCauseCollection(Collection<EventCause> eventCauses) {
		eventCause.addEventCauseCollection(eventCauses);
	}

	@Override
	public void persistFailureTypeCollection(
			Collection<FailureType> failureTypes) {
		failureType.addFailureTypeCollection(failureTypes);
	}

	@Override
	public void persistOperatorCountryCollection(
			Collection<OperatorCountry> operatorCountries) {
		operatorCountry.addOperatorCountryCollection(operatorCountries);
	}

	@Override
	public void persistUserEquipmentCollection(Collection<UserEquipment> userEquipmentCollection) {
		userEquipment.addUserEquipmentCollection(userEquipmentCollection);
	}
	
	
	@Override
    public Response addUser(User newUser) {
        Response response = new Response();
        if (user.getUserByKey(newUser.getKey()) == null) {
            user.addUser(newUser);
            response.setStatus(Response.Status.OK);
        } else {
            response.setStatus(Response.Status.ERROR);
            response.setDescription("User already exists");
        }
        return response;
    }

    @Override
    public Response updateUser(User newUser) {
        Response response = new Response();
        if (user.getUserByKey(newUser.getKey()) != null) {
            user.updateUser(newUser);
            response.setStatus(Response.Status.OK);
        } else {
            response.setStatus(Response.Status.NOT_FOUND);
            response.setDescription("User not found");
        }
        return response;
    }
	
}

