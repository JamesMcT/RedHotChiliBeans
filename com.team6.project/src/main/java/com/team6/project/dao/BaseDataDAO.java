package com.team6.project.dao;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import javax.ejb.Local;

import com.team6.project.entities.Response;
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
 * @author Cristiana Conti
 */

@Local
public interface BaseDataDAO {

	public Collection<BaseData> findByImsi(BigInteger imsi);
	
	public Collection<BaseData> findByFailureType(FailureType failureType);
	
	public Collection<BaseData> findByUserEquipment(UserEquipment userEquipment);
	
	public Collection<BaseData> findByOperatorByMCC(Integer mcc);

	public Collection<BaseData> findByOperatorCountryPK(OperatorCountryPK operatorCountryPK);
	
	public Collection<BaseData> findByEventCause(EventCausePK eventCausePK);
	
	public Collection<BaseData> getAllBaseData();

	public BaseData getBaseDataByKey(Integer id);

	public void addBaseData(BaseData baseData);
	
	public void addBaseDataCollection(Collection<BaseData> baseData);

	public void deleteBaseData(BaseData baseData);
	
	public long getBaseDataCount();
	
	public Collection<Object[]> getDistinctEventByTac(Integer ue);
	
	public long countCallFailureByTac(Integer tac, Date fromDate, Date toDate);
	
	public Response countCallFailureByTacPOST(Integer tac, Date fromDate, Date toDate);

	public Collection<Object[]> getFailureCountAndDurationPerImsiByDate(Date start, Date end);

	
//	public void updateBaseData(BaseData baseData);

//	public void deleteAll();

}
