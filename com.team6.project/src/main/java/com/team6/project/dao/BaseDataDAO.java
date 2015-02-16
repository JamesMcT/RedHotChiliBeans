package com.team6.project.dao;

import javax.ejb.Local;

import com.team6.project.entities.BaseData;

@Local
public interface BaseDataDAO {

	public BaseData getBaseDataRecord(Integer id);

	public void addNewBaseDataSet(BaseData baseData);

	public void updateBaseData(BaseData baseData);

	//public BaseData findByImsi(String imsi);

	//public void deleteByImsi(String imsi);

	public void deleteBaseDataRecord(Integer id);
	
}
