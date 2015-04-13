package com.team6.project.dao;

import java.util.Collection;

import javax.ejb.Local;

import com.team6.project.entities.EventCause;
import com.team6.project.entities.EventCausePK;

/**
 * Event Cause Data Access Object
 * <p>
 * This Event Cause data access object will allow for basic CRUD operations on EventCause objects.
 * 
 * @author John O Keeffe
 * @author Eoin Kernan
 * @author James Mc Ternan
 *
 */
@Local
public interface EventCauseDAO {

	/**
	 * Get all EventCause.
	 * 
	 * @return
	 */
	public Collection<EventCause> getAllEventCauses();

	/**
	 * Get EventCause By primary key (eventCausePK). This is a composite key
	 * made up of causeCode and eventId.
	 * 
	 * @param eventCausePK
	 * @return EventCause
	 */
	public EventCause getEventCauseByKey(EventCausePK eventCausePK);

	/**
	 * Add new EventCause record.
	 * 
	 * @param eventCause
	 */
	public void addEventCauseData(EventCause eventCause);

	/**
	 * Add multiple eventCause records.
	 * 
	 * @param eventCause
	 */
	public void addEventCauseCollection(Collection<EventCause> eventCause);

	/**
	 * Update a single EventCause record. Updated recorded must be passed.
	 * 
	 * @param eventCause
	 */
	public void updateEventCause(EventCause eventCause);

	/**
	 * Delete a single EventCause record.
	 * 
	 * @param eventCause
	 */
	public void deleteEventCause(EventCause eventCause);

}
