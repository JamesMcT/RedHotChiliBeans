package com.team6.project.dao.jpa;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.team6.project.dao.EventCauseDAO;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.EventCausePK;
import com.team6.project.entities.OperatorCountry;

/**
 * 
 * @author James
 *
 */
@Stateless
@Local
@Default
public class JPAEventCauseDAO implements EventCauseDAO {

    @PersistenceContext
    EntityManager em;
	
    @Override
	public Collection<EventCause> getAllCauses() {
    	Query query = em.createQuery("from EventCause");
		List<EventCause> results = query.getResultList();
		return results;
	}
    
    /**
     * 
     */
	@Override
	public EventCause getEventCauseByKey(EventCausePK eventCausePK) {
//		Query q = em
//				.createQuery("from EventCausePK where eventCausePK = :code"); 
//		q.setParameter("code", 2);
//		List<EventCause> result = q.getResultList(); 
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
	public void updateEventCause(
			EventCause eventCause) {
		EventCause ec = em.find(EventCause.class, eventCause);
		// Are these geters and setters required or does "Merge" take care of this in the background?
		ec = new EventCause(eventCause.getCauseCode(),eventCause.getEventId(),eventCause.getDescription());
		em.merge(ec);
	}
	
	
	/**
	 * 
	 */
	@Override
	public void deleteEventCause(EventCausePK eventCausePK) {
//		Query q = em.createQuery("from EventCausePK where eventCausePK = :code");
//		q.setParameter("code", 3);
//		List<EventCause> result = q.getResultList();
		EventCause result = em.find(EventCause.class, eventCausePK);
		em.remove(result);
	}


	
}
