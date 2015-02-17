package com.team6.project.dao;


import java.math.BigInteger;
import java.util.Collection;

import javax.ejb.Local;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.EventCausePK;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.OperatorCountryPK;
import com.team6.project.entities.UserEquipment;

@Local
public interface BaseDataDAO {
	
	public Collection<BaseData> findByImsi(BigInteger imsi);
	
	public Collection<BaseData> findByFailureType(FailureType failureType);
	
	public Collection<BaseData> findByUserEquipment(UserEquipment userEquipment);
	
	public Collection<BaseData> findByOperatorByMCC(Integer mcc);

	public Collection<BaseData> findByOperatorCountryPK(OperatorCountryPK operatorCountryPK);
	
	public Collection<BaseData> findByEventCause(EventCausePK eventCausePK);
	
	public Collection<BaseData> getAllBaseDataRecords();

	public BaseData getBaseDataRecordById(Integer id);

	public void addNewBaseDataSet(BaseData baseData);

	public void deleteBaseDataRecord(BaseData baseData);
	
//	public void updateBaseData(BaseData baseData);

//	public void deleteAll();

}
