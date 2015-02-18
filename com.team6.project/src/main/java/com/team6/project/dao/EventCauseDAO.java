package com.team6.project.dao;

import java.util.Collection;

import javax.ejb.Local;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.EventCausePK;

@Local
public interface EventCauseDAO {


	public Collection<EventCause> getAllEventCauses();

	public EventCause getEventCauseByKey(EventCausePK eventCausePK);

	public void addEventCauseData(EventCause eventCause);

	public void updateEventCause(EventCause eventCause);

	public void deleteEventCause(EventCause eventCause);

	// public EventCause findByCauseCodeAndEventId(Integer causeCode, Integer
	// eventId);

	// public void deleteByCauseCodeAndEventId(Integer causeCode, Integer
	// eventId);

	// public void deleteAll();

}
