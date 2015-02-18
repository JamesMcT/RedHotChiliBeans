package com.team6.project.dao;

import javax.ejb.Local;

import com.team6.project.entities.Record;
@Local
public interface RecordDAO {

	public void persist(Record record);

}
