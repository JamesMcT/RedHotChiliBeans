package com.team6.project.services;

import java.util.Collection;

import javax.ejb.Local;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.Record;
import com.team6.project.entities.UserEquipment;

@Local
public interface PersistenceServiceLocal {

    public Collection<EventCause> getAllEventCauses();
    public Collection<FailureType> getAllFailureTypes();
    public Collection<OperatorCountry> getAllOperatorCountries();
    public Collection<UserEquipment> getAllUserEquipment();
    public Collection<BaseData> getAllBaseData();
	
	public void persistBaseData(BaseData b);
	
	public void persistEventCause(EventCause e);
	
	public void persistFailureType(FailureType f);
	
	public void persistOperatorCountry(OperatorCountry o);
	
	public void persistUserEquipment(UserEquipment u);
	
	public void persistErroneusRecord(Record r);
}

