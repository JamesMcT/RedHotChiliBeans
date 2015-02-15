package com.team6.project.dao.jpa;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.team6.project.dao.FailureTypeDAO;
import com.team6.project.entities.FailureType;

/**
 * @author Sabee
 * 
 */

@Stateless
@Local
public class JPAFailureTypeDAO implements FailureTypeDAO{

	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public Collection<FailureType> getFailureTypes() {
		Query q = em.createQuery("from FailureType");
		return q.getResultList();
	}
	

	@Override
	public void addNewFailureTypeDataSet(FailureType failureType) {
		em.persist(failureType);		
	}

	
	@Override
	public void updateFailureType(FailureType failureType) {
		em.merge(failureType);
	}

	
	@Override
	public FailureType findByFailureCode(Integer failureCode) {
		Query q = em.createQuery("from FailureType c where c.failureCode like :failureCode")
				.setParameter("failureCode", failureCode);	
		return (FailureType) q.getSingleResult();
	}

	
	@Override
	public void deleteByFailureCode(Integer failureCode) {		
		Query q = em.createQuery("delete form FailureType where failureCode=dFailureCode like :dFailureCode")
				.setParameter("dFailureCode", failureCode);				
	}

	
	@Override
	public void deleteAll() {
		Query q = em.createNamedQuery("delete form FailureType where failureCode>=0");						
	}


}
