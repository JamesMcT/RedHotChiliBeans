package com.team6.project.dao;



import java.util.Collection;

import com.team6.project.entities.BaseData;



public interface BaseDataDAO {

	public Collection<BaseData> getBaseDataRecords();

	public void addNewBaseDataSet(BaseData baseData);

	public void updateBaseData(BaseData baseData);

	public BaseData findByImsi(String imsi);

	public void deleteByImsi(String imsi);

	public void deleteAll();

}
