package com.team6.project.dao.jpa;


import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.team6.project.dao.BaseDataDAO;
import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCausePK;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.OperatorCountryPK;
import com.team6.project.entities.Record;
import com.team6.project.entities.UserEquipment;

/**
 * 
 * @author James
 *
 */
@Stateless
@Local
public class JPABaseDataDAO implements BaseDataDAO {

	@PersistenceContext
	EntityManager em;

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
    @Override
	public Collection<BaseData> getAllBaseData() {
		Query q = em.createQuery("from BaseData");
		List<BaseData> result = q.getResultList();
		return result;
	}

	/**
	 * Return base data record via record id.
	 */
	@Override
	public BaseData getBaseDataByKey(Integer id) {
		Query q = em.createQuery("from BaseData where id = :code");
		q.setParameter("code", id);
		return (BaseData) q.getSingleResult();
	}

	/**
	 * Add new base data record to database.
	 */
	@Override
	public void addBaseData(BaseData baseData) {
		em.persist(baseData);
	}
	
	public void addBaseDataSet(Collection<BaseData> baseDataSet){
	    //EntityTransaction tx =  em.getTransaction();
	    //tx.begin();
	    for(BaseData bd : baseDataSet){
	        em.persist(bd);
	    }
	    //tx.commit();
	}

	@Override
	public void deleteBaseData(BaseData baseData) {
		em.remove(baseData);
	}

	@Override
	public Collection<BaseData> findByImsi(BigInteger imsi) {
		Query q = em.createQuery("from BaseData where imsi = :code");
		q.setParameter("code", imsi);
		@SuppressWarnings("unchecked")
        List<BaseData> result = q.getResultList();
		return result;
	}

	@Override
	public Collection<BaseData> findByFailureType(FailureType failureType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<BaseData> findByUserEquipment(UserEquipment userEquipment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<BaseData> findByOperatorByMCC(Integer mcc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<BaseData> findByEventCause(EventCausePK eventCausePK) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<BaseData> findByOperatorCountryPK(
			OperatorCountryPK operatorCountryPK) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public static void fillData(Record record, BaseData baseData){
        baseData.setCellId(record.getCellId());
        baseData.setDuration(record.getDuration());
        baseData.setImsi(record.getImsi());
        baseData.setNeVersion(record.getNeVersion());
        baseData.setHier3Id(record.getHier3Id());
        baseData.setHier32Id(record.getHier32Id());
        baseData.setHier321Id(record.getHier321Id());
    }
    
}
