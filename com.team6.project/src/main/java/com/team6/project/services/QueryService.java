package com.team6.project.services;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import javax.ejb.Local;
import javax.inject.Inject;

import com.team6.project.dao.BaseDataDAO;
import com.team6.project.dao.FailureTypeDAO;
import com.team6.project.dao.UserDAO;
import com.team6.project.dao.UserEquipmentDAO;
import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.User;
import com.team6.project.entities.UserEquipment;

/**
 * The QueryService EJB
 * 
 * @author Eoin Kernan
 * @author James Mc Ternan
 * @author Cristiana Conti
 */
@Local(QueryServiceLocal.class)
public class QueryService implements QueryServiceLocal {

	@Inject
	private UserEquipmentDAO userEquipment;

	@Inject
	private FailureTypeDAO failureType;
	/**
	 * 
	 */
	@Inject
	private UserDAO user;
	@Inject
	private BaseDataDAO baseData;

	public QueryService() {
	}

	
	/**
     * Finding all the user equipment
     * 
     * @return
     */	
	@Override
	public Collection<UserEquipment> getAllUserEquipment() {
		return userEquipment.getAllUserEquipment();
	}

	
	/**
     * Finding all the event affected by the imsi
     * 
     * @param IMSI
     * @return
     */
	@Override
	public Collection<EventCause> findByIMSI(BigInteger IMSI) {
		return baseData.findByImsi(IMSI);
	}

	
	/**
     * Finding user by id
     * 
     * @param newUserId
     * @return
     */
	@Override
	public User getUserByKey(String newUserId) {
		return user.getUserByKey(newUserId);
	}

	
	/**
     * Finding all the users
     * 
     * @return
     */
	@Override
	public Collection<User> getAllUser() {
		return user.getAllUser();
	}

	/**
     * Finding all the event caused by tac (userequipment)
     * 
     * @param ue
     * @return
     */
	@Override
	public Collection<Object[]> getDistinctEventByTac(Integer ue) {
		return baseData.getDistinctEventByTac(ue);
	}

	
	/**
     * Counting failures and duration effected IMSI in the given time period
     * 
     * @param startDate
     * @param endDate
     * @return
     */
	@Override
	public Collection<Object[]> getFailureCountAndDurationPerImsiByDate(
			Date startDate, Date endDate) {
		return baseData.getFailureCountAndDurationPerImsiByDate(startDate,
				endDate);
	}

//	@Override
//	public Collection<Object[]> findImsiByDate(Date firstDate, Date secondDate) {
//
//		return baseData.findImsiByDate(firstDate, secondDate);
//	}

	
	/**
     * Counting failures by given IMSI in the given time period
     * 
     * @param imsi
     * @param startDate
     * @param endDate
     * @return
     */
	@Override
	public Collection<BaseData> countCallFailurePerImsiByDate(BigInteger imsi, Date startDate, Date endDate) {
		return baseData.countCallFailurePerImsiByDate(imsi, startDate, endDate);
	}

	
	/**
     * Finding all the imsis
     * 
     * @return
     */
	public Collection<BigInteger> getAllImsi() {
		return baseData.getAllImsi();
	}

	
	/**
     * Finding events caused by given IMSI
     * 
     * @param imsi
     * @return
     */
	@Override
	public Collection<Object[]> getUniqueEventCauseByImsi(BigInteger imsi) {
		return baseData.getUniqueEventCauseByImsi(imsi);
	}

	
	/**
     * Finding top 10 failures by the given time period
     * 
     * @param start
     * @param end
     * @return
     */
	public Collection<Object[]> getTopTenFailuresByDate(Date start, Date end) {
		return baseData.getTopTenFailuresByDate(start, end);
	}

	
	/**
     * Counting failures and duration effected by tac in the given time period
     * 
     * @param tac
     * @param fromDate
     * @param toDate
     * @return
     */
	@Override
	public long countCallFailureByTac(Integer tac, Date fromDate, Date toDate) {

		return baseData.countCallFailureByTac(tac, fromDate, toDate);
	}

	
	/**
     * Finding top 10 occurrence failures by the given location Market/Operation/Cell and time period
     * 
     * @param fromDate
     * @param toDate
     * @return
     */
	@Override
	public Collection<Object[]> getTOP10MarketOperatorCellByDate(Date fromDate,
			Date toDate) {

		return baseData.getTOP10MarketOperatorCellByDate(fromDate, toDate);
	}

	
	/**
     * Finding IMSIs given by a failure code
     * 
     * @param fc
     * @return
     */
	@Override
	public Collection<Object[]> getImsiByFailureCode(Integer fc) {

		return baseData.getImsiByFailureCode(fc);
	}
	
	
	/**
     * Finding all the failure types
     * 
     * @return
     */
	public Collection<FailureType> getAllFailureTypes() {
		return failureType.getAllFailureTypes();
	}

	
	/**
     * Finding IMSIs for the given time period
     * 
     * @param firstDate
     * @param secondDate
     * @return
     */
	@Override
	public Collection<Object[]> findImsiByDate(Date firstDate, Date secondDate) {
		return baseData.findImsiByDate(firstDate, secondDate);
	}

}
