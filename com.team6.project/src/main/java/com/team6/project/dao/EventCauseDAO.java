package com.team6.project.dao;

import java.util.Collection;

import javax.ejb.Local;

import com.team6.project.entities.EventCause;
import com.team6.project.entities.EventCausePK;

/**
 * 
 * @author John O Keeffe
 * @author Eoin Kernan
 *
 */
@Local
public interface EventCauseDAO {

	/**
	 * 
	 * @return
	 */
	public Collection<EventCause> getAllEventCauses();

	/**
	 * 
	 * @param eventCausePK
	 * @return
	 */
	public EventCause getEventCauseByKey(EventCausePK eventCausePK);

	/**
	 * 
	 * @param eventCause
	 */
	public void addEventCauseData(EventCause eventCause);

	/**
	 * 
	 * @param eventCause
	 */
	public void addEventCauseCollection(Collection<EventCause> eventCause);

	/**
	 * 
	 * @param eventCause
	 */
	public void updateEventCause(EventCause eventCause);

	/**
	 * 
	 * @param eventCause
	 */
	public void deleteEventCause(EventCause eventCause);

	// public EventCause findByCauseCodeAndEventId(Integer causeCode, Integer
	// eventId);

	// public void deleteByCauseCodeAndEventId(Integer causeCode, Integer
	// eventId);

	// public void deleteAll();

}
