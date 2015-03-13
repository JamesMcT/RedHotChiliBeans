package com.team6.project.dao.jpa;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;

import com.team6.project.dao.FailureTypeDAO;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.FailureType;

/**
 * Concrete implementation of CRUD operations.
 * 
 * @author James Mc Ternan
 * @author Eoin Kernan
 *
 */
@Stateless
@Local
@Default
public class JPAFailureTypeDAO implements FailureTypeDAO {

	@PersistenceContext
	private EntityManager em;

	@PersistenceContext
    private Session session;
	

	@SuppressWarnings("unchecked")
    @Override
	public Collection<FailureType> getAllFailureTypes() {
		Query q = em.createQuery("from FailureType");
		List<FailureType> result = q.getResultList();
		return result;
	}

	public FailureType getFailureTypeByKey(Integer failureCode) {
		Query q = em.createQuery("from FailureType where failureCode = :code");
		q.setParameter("code", failureCode);
		return (FailureType) q.getSingleResult();
	}

	
	public void addFailureType(FailureType failureType) {
		em.persist(failureType);
	}

	@Override
	public void addFailureTypeCollection(Collection<FailureType> failureType) {
		session.beginTransaction();
    	
    	for(FailureType f:failureType){
			em.persist(f);
		}
    	
    	session.getTransaction().commit();
	}
	
	
	public void updateFailureType(FailureType failureType) {
		em.merge(failureType);
	}

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
