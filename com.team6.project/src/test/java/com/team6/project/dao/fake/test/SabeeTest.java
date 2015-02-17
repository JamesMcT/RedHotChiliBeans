package com.team6.project.dao.fake.test;



import static org.junit.Assert.assertEquals;

import java.util.Collection;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.team6.project.dao.fake.FakeEventCauseDAO;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.EventCausePK;


public class SabeeTest {

	private static Integer eventId;
	private static Integer causeCode;
	private static String description;
	private static EventCause eventCause;
	private static FakeEventCauseDAO eventDao;
			
	
	@BeforeClass
    public static void setUpBeforeClass() throws Exception {    
		eventId = 1;
		causeCode = 2;
		description = "INITIAL CTXT SETUP - test data";
		eventCause = new EventCause(eventId, causeCode , description);
				
		eventDao = new FakeEventCauseDAO();
    }
	
	
	@Test
	public void addNewEventCauseDataSetTest() {
								
		
		Collection<EventCause> allEvents;
											 	
		allEvents = eventDao.getAllEventCauses();
		
		
		
		int sizeBeforeTransaction = allEvents.size();
				
		eventDao.addNewEventCauseDataSet(eventCause);
									
		assertEquals(sizeBeforeTransaction + 1, allEvents.size());
		
		assertEquals(eventId, eventCause.getEventId()); 
		assertEquals(causeCode, eventCause.getCauseCode());
		assertEquals(description, eventCause.getDescription()); 				
	}
	
	@Test
	public void findByCauseCodeAndEventIdTest() {		
		
		//assertEquals("RCC CONN SETUP - SUCCESS", eventDao.findByCauseCodeAndEventId(0, 0).getDescription());
		//assertEquals("RCC CONN SETUP - REJECT DUE TO OVERLOAD", eventDao.findByCauseCodeAndEventId(0, 1).getDescription());
		//assertEquals("INITIAL CTXT SETUP - SUCCESS", eventDao.findByCauseCodeAndEventId(1, 0).getDescription());
		//assertEquals("INITIAL CTXT SETUP - ONGOING HANDOVER", eventDao.findByCauseCodeAndEventId(1, 1).getDescription());
		
		//assertEquals(null, eventDao.findByCauseCodeAndEventId(6, 6));
	}
		
	
	@Test
	public void deleteByCauseCodeAndEventIdTest() {

		Collection<EventCause> allEvents; 
		
		allEvents = eventDao.getAllEventCauses();
		
		int sizeBeforeTransaction = allEvents.size();	
		
		eventDao.deleteByCauseCodeAndEventId(1, 0);
				
		assertEquals(sizeBeforeTransaction -1, allEvents.size());
			
	}
	
	@Test
	public void deleteAllTest() {
				 									
		eventDao.deleteAll();
		
		Collection<EventCause> allEvents = eventDao.getAllEventCauses();
		
		assertEquals(0, allEvents.size());
			
	}

}
