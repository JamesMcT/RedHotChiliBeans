package com.team6.project.dao.jpa;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.team6.project.dao.BaseDataDAO;
import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.EventCausePK;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.OperatorCountryPK;
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
	@Override
	public Collection<BaseData> getAllBaseDataRecords() {
		Query q = em.createQuery("from BaseData");
		List<BaseData> result = q.getResultList();
		return result;
	}

	/**
	 * Return base data record via record id.
	 */
	@Override
	public BaseData getBaseDataRecordById(Integer id) {
		Query q = em.createQuery("from BaseData where id = :code");
		q.setParameter("code", id);
		List<BaseData> result = q.getResultList();
		return result.get(0);
	}

	/**
	 * Add new base data record to database.
	 */
	@Override
	public void addNewBaseDataSet(BaseData baseData) {
		em.persist(baseData);
	}

	@Override
	public void deleteBaseDataRecord(BaseData baseData) {
		em.remove(baseData);
	}

	@Override
	public Collection<BaseData> findByImsi(BigInteger imsi) {
		Query q = em.createQuery("from BaseData where imsi = :code");
		q.setParameter("code", imsi);
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

}
