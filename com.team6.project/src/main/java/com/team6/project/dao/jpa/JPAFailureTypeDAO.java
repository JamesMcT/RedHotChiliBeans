package com.team6.project.dao.jpa;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.team6.project.dao.FailureTypeDAO;
import com.team6.project.entities.FailureType;

/**
 * Concrete implementation of CRUD operations.
 * 
 * @author James
 *
 */

@Stateless
@Local
@Default
public class JPAFailureTypeDAO implements FailureTypeDAO {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Returns collection of FailureType
	 */
	@SuppressWarnings("unchecked")
    @Override
	public Collection<FailureType> getAllFailureTypes() {
		Query q = em.createQuery("from FailureType");
		List<FailureType> result = q.getResultList();
		return result;
	}

	/**
	 * Get failure Type by failure code.
	 * 
	 * @param failureCode
	 * @return FailureType
	 */
	public FailureType getFailureTypeByKey(Integer failureCode) {
		Query q = em.createQuery("from FailureType where failureCode = :code");
		q.setParameter("code", failureCode);
		return (FailureType) q.getSingleResult();
	}

	/**
	 * 
	 * @param failureType
	 */
	public void addFailureType(FailureType failureType) {
		em.persist(failureType);
	}

	/**
	 * 
	 * @param failureType
	 */

	public void updateFailureType(FailureType failureType) {
		em.merge(failureType);
	}

	/**
	 * Delete passed FailureType
	 */
	@Override
	public void deleteFailureType(FailureType failureType) {
		em.remove(failureType);
	}

// Currently unused - May be added in at a later stage.
//	/**
//	 * Delete FailureType by failure code.
//	 */
//	public void deleteFailureTypeByFailureCode(Integer failureCode) {
//		Query q = em.createQuery("from FailureType where failureCode = :code");
//		q.setParameter("code", failureCode);
//		em.remove(q.getSingleResult());
//	}

}
