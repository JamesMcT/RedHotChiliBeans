package com.team6.project.dao.jpa.test;

import static org.junit.Assert.assertEquals;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.team6.project.entities.EventCause;

@RunWith(Arquillian.class)
public class JPAEventCauseDAOTest extends JPADAOTest{

   
    private EventCause eventCause;

   @Before
    public void preparePersistenceTest() throws Exception {
        eventCause = new EventCause(1, 2, "desc Event Cause");
        clear();
        insertData();
    }

    private void insertData() throws Exception {
        eventCauseDAO.addEventCauseData(eventCause);
    }
    
    private void clear() throws Exception {
        eventCauseDAO.deleteEventCause(eventCause);
    }

    @Test
    public void testEventCause() {
        EventCause ec = eventCauseDAO.getEventCauseByKey(eventCause.getKey());
        assertEquals(ec, eventCause);
    }
  
}
