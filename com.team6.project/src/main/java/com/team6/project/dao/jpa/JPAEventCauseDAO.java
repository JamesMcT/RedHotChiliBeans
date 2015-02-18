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
	public Collection<EventCause> getAllEventCauses() {
		Query q = em.createQuery("from EventCause");
		List<EventCause> result = q.getResultList();
		return result;
	}

	/**
     * 
     */
	@Override
	public EventCause getEventCauseByPK(EventCausePK eventCausePK) {
		return em.find(EventCause.class, eventCausePK);
	}

	/**
	 * 
	 */
	@Override
	public void addNewEventCauseDataSet(EventCause eventCause) {
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
	
