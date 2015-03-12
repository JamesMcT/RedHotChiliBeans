package com.team6.project.dao;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import javax.ejb.Local;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCausePK;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.OperatorCountryPK;
import com.team6.project.entities.UserEquipment;

/**
 * The base data DAO interface.
 * 
 * @author John O Keeffe
 * @author Eoin Kernan
 *
 */
@Local
public interface BaseDataDAO {

	/**
	 * 
	 * @param imsi
	 * @return
	 */
	public Collection<BaseData> findByImsi(BigInteger imsi);
	
	/**
	 * 
	 * @param failureType
	 * @return
	 */
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
	
//	public void updateBaseData(BaseData baseData);

//	public void deleteAll();

}
