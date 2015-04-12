package com.team6.project.entities.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.team6.project.entities.UserEquipment;

public class UserEquipmentTest {

    private static UserEquipment ue;

    @Before
    public void setUpBeforeClass() throws Exception {
        ue = new UserEquipment(2, "marketingName", "manufacturer",
                                "accessCapability", "model", "vendorName",
                                "type", "os", "inputMode");

    }

    @Test
    public void getKeyTest_True() {
        assertEquals(ue.getKey(), new Integer(2));
    }

    @Test
    public void getKeyTest_False() {
        assertNotEquals(ue.getKey(), new Integer(1));
    }

    @Test
    public void toStringTest() {
        assertEquals(
                ue.toString(),
                "Tac : 2 Marketing Name : marketingName Manufacturer : manufacturer Access Capability : accessCapability Model : model Vendor Name : vendorName Type : type Operating System : os Input Mode : inputMode");
    }

    @Test
    public void equalsTrueTest() {
        UserEquipment other = new UserEquipment(2, "marketingName",
                                                "manufacturer",
                                                "accessCapability", "model",
                                                "vendorName", "type", "os",
                                                "inputMode");
        assertTrue(ue.equals(other));
        assertTrue(ue.hashCode() == other.hashCode());
    }

    @Test
    public void equalsFalseTest_DiffTac() {
        UserEquipment other = new UserEquipment(9, "marketingName",
                                                "manufacturer",
                                                "accessCapability", "model",
                                                "vendorName", "type", "os",
                                                "inputMode");
        assertFalse(ue.equals(other));
        assertFalse(ue.hashCode() == other.hashCode());
    }

    @Test
    public void equalsFalseTest_NoTac() {
        UserEquipment other = new UserEquipment(null, "marketingName",
                                                "manufacturer",
                                                "accessCapability", "model",
                                                "vendorName", "type", "os",
                                                "inputMode");
        assertFalse(ue.equals(other));
        assertFalse(ue.hashCode() == other.hashCode());
    }

    @Test
    public void hasRequiredFieldsTest_NoTac() {
        ue.setTac(null);
        assertFalse(ue.hasRequiredFields());
    }

    @Test
    public void hasRequiredFieldsTest() {
        assertTrue(ue.hasRequiredFields());
    }

    @Test
    public void hasRequiredFieldsTest_NoOtherFields() {
        assertTrue(ue.hasRequiredFields());
    }

}
