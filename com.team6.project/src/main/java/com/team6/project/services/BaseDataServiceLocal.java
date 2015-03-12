package com.team6.project.services;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import com.team6.project.entities.BaseData;

/**
 * 
 * @author 
 *
 */
public interface BaseDataServiceLocal {

	/**
	 * 
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public Collection<BaseData> findImsiByDate(Date firstDate, Date secondDate);

	
}
