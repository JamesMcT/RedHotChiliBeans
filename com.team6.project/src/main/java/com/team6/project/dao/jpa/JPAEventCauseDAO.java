package com.team6.project.dao.jpa;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.team6.project.dao.EventCauseDAO;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.EventCausePK;
<<<<<<< HEAD
import com.team6.project.entities.FailureType;
import com.team6.project.entities.OperatorCountry;

/**
 * 
 * @author James
 *
 */
@Stateless
@Local
public class JPAEventCauseDAO implements EventCauseDAO {

	@PersistenceContext
	EntityManager em;

	/**
	 * 
	 */
	@Override
=======

/**
 * 
 * @author James
 *
 */
@Stateless
@Local
public class JPAEventCauseDAO implements EventCauseDAO {

	@PersistenceContext
	EntityManager em;

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
    @Override
>>>>>>> refs/heads/readers_branch_cris
	public Collection<EventCause> getAllEventCauses() {
		Query q = em.createQuery("from EventCause");
		List<EventCause> result = q.getResultList();
		return result;
	}

	/**
     * 
     */
	@Override
	public EventCause getEventCauseByKey(EventCausePK eventCausePK) {
		return em.find(EventCause.class, eventCausePK);
	}

	/**
	 * 
	 */
	@Override
	public void addEventCauseData(EventCause eventCause) {
		em.persist(eventCause);
	}

	/**
	 * 
	 */
	@Override
	public void updateEventCause(EventCause eventCause) {
		em.merge(eventCause);
	}

	/**
	 * 
	 */
	@Override
	public void deleteEventCause(EventCause eventCause) {
		em.remove(eventCause);
	}

}
	
