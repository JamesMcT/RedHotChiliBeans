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
import com.team6.project.dao.UserEquipmentDAO;
import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.Record;
import com.team6.project.entities.UserEquipment;


public class PersistenceServiceFake implements PersistenceServiceLocal{
    
   

    @Override
    public Collection<EventCause> getAllEventCauses() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<FailureType> getAllFailureTypes() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<OperatorCountry> getAllOperatorCountries() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<UserEquipment> getAllUserEquipment() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<BaseData> getAllBaseData() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void persistBaseData(BaseData b) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void persistEventCause(EventCause e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void persistFailureType(FailureType f) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void persistOperatorCountry(OperatorCountry o) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void persistUserEquipment(UserEquipment u) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void persistErroneusRecord(Record r) {
        // TODO Auto-generated method stub
        
    }

}
