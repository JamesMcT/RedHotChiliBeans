package com.team6.project.dao.jpa;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;

import com.team6.project.dao.OperatorCountryDAO;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.OperatorCountryPK;

/**
 * 
 * @author James Mc Ternan
 * @author Eoin Kernan
 *
 */
@Stateless
@Local
public class JPAOperatorCountryDAO implements OperatorCountryDAO {

	@PersistenceContext
	private EntityManager em;

	@PersistenceContext
    private Session session;
	
	@SuppressWarnings("unchecked")
    @Override
	public Collection<OperatorCountry> getAllOperatorCountries() {
		Query q = em.createQuery("from OperatorCountry");
		List<OperatorCountry> result = q.getResultList();
		return result;
	}

	
	@Override
	public OperatorCountry getOperatorCountryByKey(
			OperatorCountryPK operatorCountryPK) {
		return em.find(OperatorCountry.class, operatorCountryPK);
	}

	@Override
	public void addOperatorCountry(OperatorCountry operatorCountry) {
		em.persist(operatorCountry);
	}

	@Override
	public void addOperatorCountryCollection(Collection<OperatorCountry> operatorCountry) {
		session.beginTransaction();
    	
    	for(OperatorCountry o:operatorCountry){
			em.persist(o);
		}
    	
    	session.getTransaction().commit();
	}
	
	
	@Override
	public void updateOperatorCountry(OperatorCountry operatorCountry) {
		em.merge(operatorCountry);
	}

	@Override
	public void deleteOperatorCountry(OperatorCountry operatorCountry) {
		em.remove(operatorCountry);
	}

	// @Override
	// public OperatorCountry findByOperatorCountry(Integer mcc, Integer mnc) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	// @Override
	// public void deleteByMccAndMnc(Integer mcc) {
	// // TODO Auto-generated method stub
	//
	// }

}


