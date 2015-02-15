package com.team6.project.dao;

import java.util.Collection;

import com.team6.project.entities.EventCause;



public interface EventCauseDAO {

	public Collection<EventCause> getAllEventCauses();
	
	public void addNewEventCauseDataSet(EventCause eventCause);

	public void updateEventCause(EventCause eventCause);

	public EventCause findByCauseCodeAndEventId(Integer causeCode, Integer eventId);

	public void deleteByCauseCodeAndEventId(Integer causeCode, Integer eventId);

	public void deleteAll();
	
}
