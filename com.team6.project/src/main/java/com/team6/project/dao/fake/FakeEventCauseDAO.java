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

	//private static  Map<Integer, EventCause> events = new HashMap<Integer, EventCause>();
	
	private static Map<EventCausePK, EventCause> events = new HashMap<EventCausePK, EventCause>();
	
    static {
    	
    	events.put(new EventCausePK(0,0), new EventCause(0, 0, "RCC CONN SETUP - SUCCESS"));
    	
    	//TODO: Fix like line above
    	events.put(962, new EventCause(0, 1, "RCC CONN SETUP - REJECT DUE TO OVERLOAD"));
    	events.put(992, new EventCause(1, 0, "INITIAL CTXT SETUP - SUCCESS"));
    	events.put(993, new EventCause(1, 1, "INITIAL CTXT SETUP - ONGOING HANDOVER"));
    }
	
	
	@Override
	public Collection<EventCause> getAllEventCauses() {		
		return events.values();
	}

	@Override
	public void addNewEventCauseDataSet(EventCause eventCause) {
		EventCausePK ocPK = new EventCausePK(eventCause.getCauseCode(), eventCause.getEventId());
		
		events.put(ocPK.hashCode(), eventCause);		
	}

	@Override
	public void updateEventCause(EventCause eventCause) {
		EventCausePK ocPK = new EventCausePK(eventCause.getCauseCode(), eventCause.getEventId());
		
		events.put(ocPK.hashCode(), eventCause);
	}

	@Override
	public EventCause findByCauseCodeAndEventId(Integer causeCode, Integer eventId) {
		EventCausePK ocPK = new EventCausePK(causeCode, eventId);
		
		return events.get(ocPK);
	}

	@Override
	public void deleteByCauseCodeAndEventId(Integer causeCode, Integer eventId) {
		EventCausePK ocPK = new EventCausePK(causeCode, eventId);
		events.remove(ocPK);
		
	}

	@Override
	public void deleteAll() {
		events.clear();
		
	}

	
	
	
	
}
