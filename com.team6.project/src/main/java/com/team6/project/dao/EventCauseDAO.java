package com.team6.project.dao;

import javax.ejb.Local;

import com.team6.project.entities.EventCause;
import com.team6.project.entities.EventCausePK;

@Local
public interface EventCauseDAO {

	public EventCause getEventCause(EventCausePK eventCausePK);
	
	public void addNewEventCauseDataSet(EventCause eventCause);

	public void updateEventCause(EventCause eventCause);

	public void deleteEventCause(EventCausePK eventCausePK);

	
}
