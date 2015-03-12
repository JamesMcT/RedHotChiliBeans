package com.team6.project.dao;

import java.util.Collection;

import javax.ejb.Local;

import com.team6.project.entities.Record;

/**
 * 
 * @author John O Keeffe
 *
 */
@Local
public interface RecordDAO {

	/**
	 * 
	 * @param record
	 */
	public void addRecord(Record record);

	/**
	 * 
	 * @param r
	 */
	public void addRecordCollection(Collection<Record> r);

	/**
	 * 
	 * @param record
	 */
	public void deleteRecord(Record record);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Record getRecordByKey(int id);
	
	/**
	 * 
	 * @return
	 */
	public long getRecordCount();

}
