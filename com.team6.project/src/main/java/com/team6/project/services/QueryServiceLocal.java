package com.team6.project.services;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import javax.ejb.Local;
import javax.persistence.Query;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.User;
import com.team6.project.entities.UserEquipment;
/**
 * The QueryService local interface.
 * 
 * @author Eoin Kernan
 * @author James Mc Ternan
 *
 */
@Local
public interface QueryServiceLocal {
	
	/**
     * Finding all the user equipment
     * 
     * @return
     */	
	public Collection<UserEquipment> getAllUserEquipment();
	
	
	/**
     * Finding all the event affected by the imsi
     * 
     * @param IMSI
     * @return
     */
	public Collection<EventCause> findByIMSI(BigInteger IMSI);

	
	/**
     * Finding user by id
     * 
     * @param newUserId
     * @return
     */
	public User getUserByKey(String newUserId);
	
	
	/**
     * Finding all the users
     * 
     * @return
     */
	public Collection<User> getAllUser();

	
	/**
     * Finding all the event caused by tac (userequipment)
     * 
     * @param ue
     * @return
     */
	public Collection<Object[]> getDistinctEventByTac(Integer ue);
	
	
	
	public long countCallFailureByTac(Integer tac, Date fromDate, Date toDate);
	
	public Collection<Object[]> getTOP10MarketOperatorCellByDate(Date fromDate, Date toDate);
	
	
	/**
     * Counting failures and duration effected IMSI in the given time period
     * 
     * @param startDate
     * @param endDate
     * @return
     */
	public Collection<Object[]> getFailureCountAndDurationPerImsiByDate(Date startDate, Date endDate);


//	public Collection<BaseData> findImsiByDate(Date firstDate, Date secondDate);
	
	
	/**
     * Counting failures by given IMSI in the given time period
     * 
     * @param imsi
     * @param startDate
     * @param endDate
     * @return
     */
	public Collection<BaseData> countCallFailurePerImsiByDate(BigInteger imsi, Date startDate, Date endDate);

	public Collection<Object[]> findImsiByDate(Date firstDate, Date secondDate);


	/**
     * Finding all the imsis
     * 
     * @return
     */
	public Collection<BigInteger> getAllImsi();
	
	
	/**
     * @return All the unique EventCause objects and the 
     * number of their occurrences associated to a specific imsi. 
     */
	/**
     * Finding events caused by given IMSI
     * 
     * @param imsi
     * @return
     */
	public Collection<Object[]> getUniqueEventCauseByImsi(BigInteger imsi);
	public Collection<Object[]> getTopTenFailuresByDate(Date start, Date end);
	
    public Collection<Object[]> getImsiByFailureCode(Integer fc);
    
	public Collection<FailureType> getAllFailureTypes();



}
