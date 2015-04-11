package com.team6.project.entities.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.team6.project.entities.EventCausePK;

public class EventCausePKTest {

    private static EventCausePK ecPK;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        ecPK = new EventCausePK(1, 2);
    }

    @Test
    public void toStringTest() {
        assertEquals(ecPK.toString(), "Event Id : 1 Cause code: 2");
    }

    @Test
    public void equalsTrueTest() {
        EventCausePK ecOther = new EventCausePK(1, 2);
        assertTrue(ecPK.hashCode() == ecOther.hashCode());
        assertTrue(ecPK.equals(ecOther));
    }

    @Test
    public void equalsFalseTest_EventId() {
        EventCausePK ecOther = new EventCausePK(null, 2);
        assertFalse(ecPK.hashCode() == ecOther.hashCode());
        assertFalse(ecPK.equals(ecOther));
    }

    @Test
    public void equalsFalseTest_CauseCode() {
        EventCausePK ecOther = new EventCausePK(1, null);
        assertFalse(ecPK.hashCode() == ecOther.hashCode());
        assertFalse(ecPK.equals(ecOther));
    }

}
