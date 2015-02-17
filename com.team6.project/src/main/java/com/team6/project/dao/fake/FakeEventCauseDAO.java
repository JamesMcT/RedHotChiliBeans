package com.team6.project.dao.fake;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;

import com.team6.project.dao.EventCauseDAO;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.EventCausePK;



@Stateless
@Local
public class FakeEventCauseDAO implements EventCauseDAO{

	
	private static Map<EventCausePK, EventCause> events = new HashMap<EventCausePK, EventCause>();
	
    static {    	
    	events.put(new EventCausePK(0, 0), new EventCause(0, 0, "RCC CONN SETUP - SUCCESS"));    	
    	events.put(new EventCausePK(0, 1), new EventCause(0, 1, "RCC CONN SETUP - REJECT DUE TO OVERLOAD"));
    	events.put(new EventCausePK(1, 0), new EventCause(1, 0, "INITIAL CTXT SETUP - SUCCESS"));
    	events.put(new EventCausePK(1, 1), new EventCause(1, 1, "INITIAL CTXT SETUP - ONGOING HANDOVER"));
    }
	
	
	@Override
	public Collection<EventCause> getAllEventCauses() {		
		return events.values();
	}

	@Override
	public void addNewEventCauseDataSet(EventCause eventCause) {		
		events.put(new EventCausePK(eventCause.getCauseCode(), eventCause.getEventId()), eventCause);		
	}

	@Override
	public void updateEventCause(EventCause eventCause) {
		//it does the same on FakeDAO
		addNewEventCauseDataSet(eventCause);
	}

	@Override
	public EventCause findByCauseCodeAndEventId(Integer causeCode, Integer eventId) {				
		return events.get(new EventCausePK(causeCode, eventId));
	}

	@Override
	public void deleteByCauseCodeAndEventId(Integer causeCode, Integer eventId) {		
		events.remove(new EventCausePK(causeCode, eventId));		
	}

	@Override
	public void deleteAll() {
		events.clear();
		
	}
	
}
