package com.team6.project.dao.jpa;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.team6.project.dao.OperatorCountryDAO;
import com.team6.project.entities.BaseData;
import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.OperatorCountryPK;

@Stateless
@Local
@Default
public class JPAOperatorCountryDAO implements OperatorCountryDAO {

	@PersistenceContext
	private EntityManager em;

	public Collection<OperatorCountry> getAllOperatorCountries(){
    	Query query = em.createQuery("from BaseData");
		List<OperatorCountry> results = query.getResultList();
		return results;
    }
	
	/**
	 * 
	 * @param operatorCountryPK
	 * @return
	 */
	@Override
	public OperatorCountry getOperatorCountry(
			OperatorCountryPK operatorCountryPK) {
		//Query q = em.createQuery("from OperatorCountryPK where operatorCountryPK = :code"); 
		// Car car = em.find(Car.class, carPK);
		return em.find(OperatorCountry.class, operatorCountryPK);
		//q.setParameter("code", 2);
		//List<OperatorCountry> result = q.getResultList(); // I have returned a
															// single result? IS
															// THIS CORRECT.
															// INTERFACE
															// SUGGESTS TO
															// RETURN A
															// LIST/COLLECTION
		// return result.get(0);

	}

	/**
	 * 
	 * @param operatorCountry
	 */
	@Override
	public void addNewOperatorCountryDataSet(OperatorCountry operatorCountry) {
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
	public void updateOperatorCountry(OperatorCountryPK operatorCountryPK,
			OperatorCountry operatorCountry) {
		OperatorCountry oc = getOperatorCountry(operatorCountryPK);
		oc = new OperatorCountry(operatorCountry.getMcc(),
				operatorCountry.getMnc(), operatorCountry.getCountry(),
				operatorCountry.getOperator());
		em.merge(oc);

	}

	/**
	 * Delete operatorCountry record by composite key (OperatorCountryPK)
	 * 
	 * @param operatorCountryPK
	 */
	@Override
	public void deleteOperatorCountry(OperatorCountryPK operatorCountryPK) {
//		Query q = em
//				.createQuery("from OperatorCountryPK where operatorCountryPK = :code");
//		q.setParameter("code", 3);
//		List<OperatorCountry> result = q.getResultList();
		OperatorCountry result = em.find(OperatorCountry.class, operatorCountryPK);
		em.remove(result);

	}

}
