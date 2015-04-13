package com.team6.project.dao;

import java.util.Collection;

import javax.ejb.Local;

import com.team6.project.entities.Record;

/**
 * Provides database interaction for Record objects.
 * <p>
 * As Record is an erroneous record there is minimal functionality required thus
 * only the basic CRUD operations have been implemented.
 * 
 * @author John O Keeffe
 * @author Cristiana Conti
 * @author James Mc Ternan
 *
 */
@Local
public interface RecordDAO {

	/**
	 * Add single record.
	 * 
	 * @param record
	 */
	public void addRecord(Record record);

	/**
	 * Add multiple records.
	 * 
	 * @param r
	 */
	public void addRecordCollection(Collection<Record> r);

	/**
	 * Remove single record.
	 * 
	 * @param record
	 */
	public void deleteRecord(Record record);

	/**
	 * Get records by Id.
	 * 
	 * @param id
	 * @return
	 */
	public Record getRecordByKey(int id);

	/**
	 * Get total count of records.
	 * 
	 * @return
	 */
	public long getRecordCount();

}
