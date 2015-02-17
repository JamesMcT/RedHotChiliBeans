package com.team6.project.services;

import java.util.Collection;

import javax.ejb.Local;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.UserEquipment;

@Local
public interface PersistenceServiceLocal {

	Collection<EventCause> getAllEventCauses();
	Collection<FailureType> getAllFailureTypes();
	Collection<OperatorCountry> getAllOperatorCountries();
	Collection<UserEquipment> getAllUserEquipment();
	Collection<BaseData> getAllBaseData();
}
