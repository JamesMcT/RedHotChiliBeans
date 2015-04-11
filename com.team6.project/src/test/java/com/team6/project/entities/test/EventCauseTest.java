package com.team6.project.entities.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.team6.project.entities.EventCause;
import com.team6.project.entities.EventCausePK;

public class EventCauseTest {


    private static EventCause ec;

    
    @Before
    public void setUpBeforeClass() throws Exception {
        ec = new EventCause(1, 2, "description");
    }
    
    @Test
    public void getKeyTest_True() {
        assertEquals(ec.getKey(), new EventCausePK(1,2));
    }
    
    @Test
    public void getKeyTest_False() {
        assertNotEquals(ec.getKey(), new EventCausePK(2,1));
    }

    @Test
    public void toStringTest() {
        assertEquals(ec.toString(), "Event Id : 1 Cause code: 2 Description: description");
    }
    
    @Test
    public void equalsTrueTest() {
        EventCause ecOther = new EventCause(1, 2, "description");
        assertTrue(ec.hashCode() == ecOther.hashCode());
        assertTrue(ec.equals(ecOther));
    }
    @Test
    public void equalsFalseTest_EventId() {
        EventCause ecOther = new EventCause(null, 2, "description");
        assertFalse(ec.equals(ecOther));
        assertFalse(ec.hashCode() == ecOther.hashCode());
    }
    @Test
    public void equalsFalseTest_CauseCode() {
        EventCause ecOther = new EventCause(1, null, "description");
        assertFalse(ec.equals(ecOther));
        assertFalse(ec.hashCode() == ecOther.hashCode());
    }
    
    @Test
    public void hasRequiredFieldsTest_NoCauseCode() {
        ec.setCauseCode(null);
        assertFalse(ec.hasRequiredFields());
    }
    @Test
    public void hasRequiredFieldsTest_NoEventId() {
        ec.setEventId(null);
        assertFalse(ec.hasRequiredFields());
    }
    
    @Test
    public void hasRequiredFieldsTest() {
        assertTrue(ec.hasRequiredFields());
    }
    
    @Test
    public void hasRequiredFieldsTest_NoDescription() {
        ec.setDescription(null);
        assertTrue(ec.hasRequiredFields());
    }
    
    @Test
    public void hasRequiredFieldsTest_NoBoth() {
        ec.setEventId(null);
        ec.setCauseCode(null);
        assertFalse(ec.hasRequiredFields());
    }

}