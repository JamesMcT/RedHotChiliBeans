package com.team6.project.dao;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import javax.ejb.Local;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.EventCausePK;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.OperatorCountryPK;
import com.team6.project.entities.UserEquipment;

/**
 * The base data DAO interface.
 * 
 * @author John O Keeffe
 * @author Eoin Kernan
 * @author Cristiana Conti
 * @author Sabee
 */
@Local
public interface BaseDataDAO {


	/**
	 * 
	 * @param failureType
	 * @return
	 */
	public Collection<EventCause> findByImsi(BigInteger imsi);

	public Collection<BaseData> findByFailureType(FailureType failureType);
	
	/**
	 * 
	 * @param userEquipment
	 * @return
	 */
	public Collection<BaseData> findByUserEquipment(UserEquipment userEquipment);
	
	/**
	 * 
	 * @param mcc
	 * @return
	 */
	public Collection<BaseData> findByOperatorByMCC(Integer mcc);

	/**
	 * 
	 * @param operatorCountryPK
	 * @return
	 */
	public Collection<BaseData> findByOperatorCountryPK(OperatorCountryPK operatorCountryPK);
	
	/**
	 * 
	 * @param eventCausePK
	 * @return
	 */
	public Collection<BaseData> findByEventCause(EventCausePK eventCausePK);
	
	/**
	 * 
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public Collection<BaseData> findImsiByDate(Date firstDate, Date secondDate);
	
	/**
	 * 
	 * @return
	 */
	public Collection<BaseData> getAllBaseData();

	/**
	 * 
	 * @param id
	 * @return
	 */
	public BaseData getBaseDataByKey(Integer id);

	/**
	 * 
	 * @param baseData
	 */
	public void addBaseData(BaseData baseData);
	
	/**
	 * 
	 * @param baseData
	 */
	public void addBaseDataCollection(Collection<BaseData> baseData);

	/**
	 * 
	 * @param baseData
	 */
	public void deleteBaseData(BaseData baseData);
	
	/**
	 * 
	 * @return
	 */
	public long getBaseDataCount();
	
	
	public Collection<Object[]> getDistinctEventByTac(Integer ue);
	
	
	/**
	 * @param tac
	 * @param fromDate
	 * @param toDate
	 * 
	 * @return long
	 * Return number of failure caused by tac in a given time period
	 */
	
	public long countCallFailureByTac(Integer tac, Date fromDate, Date toDate);
	

	public Collection<Object[]> getFailureCountAndDurationPerImsiByDate(Date start, Date end);
	
	
	public Collection<Object[]> getTopTenFailuresByDate(Date start, Date end);

    public Collection<Object[]> getImsiByFailureCode(Integer fc);

	/**
	 * Return all IMSI values.
	 * @return
	 */
	public Collection<BigInteger> getAllImsi();
	/**
     * @return All the unique EventCause objects and the 
     * number of their occurrences associated to a specific imsi. 
     */
	public Collection<Object[]> getUniqueEventCauseByImsi(BigInteger imsi);

	
	/**
	 * @param fromDate
	 * @param toDate
	 * 
	 * @return Collection<BaseData>
	 * Return the top 10 Market/Operator/Cell ID cause the failure in a given time period 
	 */
	
	public Collection<Object[]> getTOP10MarketOperatorCellByDate(Date fromDate, Date toDate);

	
//	public void updateBaseData(BaseData baseData);

//	public void deleteAll();

}
