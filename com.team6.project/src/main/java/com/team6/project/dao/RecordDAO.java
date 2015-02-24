package com.team6.project.dao;

import java.util.Collection;

import javax.ejb.Local;

import com.team6.project.entities.Record;
@Local
public interface RecordDAO {

	public void addRecord(Record record);

	public void addRecordCollection(Collection<Record> r);

	public void deleteRecord(Record record);
	
	public Record getRecordByKey(int id);

}
