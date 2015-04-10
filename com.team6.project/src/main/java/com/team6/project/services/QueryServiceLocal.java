package com.team6.project.services;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import javax.ejb.Local;

import javax.persistence.Query;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.User;
import com.team6.project.entities.UserEquipment;
/**
 * The QueryService local interface.
 * 
 * @author Eoin Kernan
 *
 */
@Local
public interface QueryServiceLocal {
	
	/**
	 * 
	 * @return
	 */
	public Collection<UserEquipment> getAllUserEquipment();
	
	public Collection<EventCause> findByIMSI(BigInteger IMSI);

	public User getUserByKey(String newUserId);
	
	public Collection<User> getAllUser();

	
	public Collection<Object[]> getDistinctEventByTac(Integer ue);
	
	public long countCallFailureByTac(Integer tac, Date fromDate, Date toDate);
	
	public Collection<Object[]> getTOP10MarketOperatorCellByDate(Date fromDate, Date toDate);
	
	public Collection<Object[]> getFailureCountAndDurationPerImsiByDate(Date startDate, Date endDate);

	public Collection<BaseData> findImsiByDate(Date firstDate, Date secondDate);


	
	public Collection<BigInteger> getAllImsi();
	/**
     * @return All the unique EventCause objects and the 
     * number of their occurrences associated to a specific imsi. 
     */
	public Collection<Object[]> getUniqueEventCauseByImsi(BigInteger imsi);
	public Collection<Object[]> getTopTenFailuresByDate(Date start, Date end);
	
    public Collection<BaseData> getImsiByFailureCode(Integer fc);

}
