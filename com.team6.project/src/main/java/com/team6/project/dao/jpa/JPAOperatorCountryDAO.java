package com.team6.project.dao.jpa;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.team6.project.dao.OperatorCountryDAO;
import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.OperatorCountryPK;

/**
 * 
 * @author James
 *
 */
@Stateless
@Local
public class JPAOperatorCountryDAO implements OperatorCountryDAO {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Returns collection of OperatorCountry
	 */
	@SuppressWarnings("unchecked")
    @Override
	public Collection<OperatorCountry> getAllOperatorCountries() {
		Query q = em.createQuery("from Operator");
		List<OperatorCountry> result = q.getResultList();
		return result;
	}

	/**
	 * 
	 * @param operatorCountryPK
	 * 
	 */
	@Override
	public OperatorCountry getOperatorCountryByKey(
			OperatorCountryPK operatorCountryPK) {
		return em.find(OperatorCountry.class, operatorCountryPK);
	}

	/**
	 * 
	 * @param operatorCountry
	 */
	@Override
	public void addOperatorCountry(OperatorCountry operatorCountry) {
		em.persist(operatorCountry);
	}

	/**
	 * Update existing OperatorCountry, record to be updated returned via PK.
	 * Updated OperatorCountry also passed.
	 * 
	 * @param operatorCountryPK
	 * @param operatorCountry
	 */
	@Override
	public void updateOperatorCountry(OperatorCountry operatorCountry) {
		em.merge(operatorCountry);
	}

	/**
	 * Delete operatorCountry record by composite key (OperatorCountryPK)
	 * 
	 * @param operatorCountryPK
	 */
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


