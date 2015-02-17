package com.team6.project.entities.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.UserEquipment;

public class UserEquipmentTest {

    private static UserEquipment ue1;
    private static UserEquipment ue2;
    private static UserEquipment ue3;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        ue1 = new UserEquipment(null, "marketingName", "manufacturer",
                                "accessCapability", "model", "vendorName",
                                "type", "os", "inputMode");
        ue2 = new UserEquipment(2, "marketingName", "manufacturer",
                                "accessCapability", "model", "vendorName",
                                "type", "os", "inputMode");
        ue3 = new UserEquipment(1, null, null, null, null, null, null, null,
                                null);

    }

    @Test
    public void toStringTest() {
        assertEquals(ue2.toString(),
                     "Tac : 2 Marketing Name : marketingName Manufacturer : manufacturer Access Capability : accessCapability Model : model Vendor Name : vendorName Type : type Operating System : os Input Mode : inputMode");
    }

    @Test
    public void equalsTrueTest() {
        UserEquipment other = new UserEquipment(2, "marketingName",
                                                "manufacturer",
                                                "accessCapability", "model",
                                                "vendorName", "type", "os",
                                                "inputMode");
        assertTrue(ue2.equals(other));
    }

    @Test
    public void equalsFalseTest_DiffTac() {
        UserEquipment other = new UserEquipment(9, "marketingName",
                                                "manufacturer",
                                                "accessCapability", "model",
                                                "vendorName", "type", "os",
                                                "inputMode");
        assertFalse(ue2.equals(other));
    }

    @Test
    public void equalsFalseTest_NoTac() {
        UserEquipment other = new UserEquipment(null, "marketingName",
                                                "manufacturer",
                                                "accessCapability", "model",
                                                "vendorName", "type", "os",
                                                "inputMode");
        assertFalse(ue2.equals(other));
    }

    @Test
    public void hasRequiredFieldsTest_NoTac() {
        assertFalse(ue1.hasRequiredFields());
    }

    @Test
    public void hasRequiredFieldsTest() {
        assertTrue(ue2.hasRequiredFields());
    }

    @Test
    public void hasRequiredFieldsTest_NoOtherFields() {
        assertTrue(ue2.hasRequiredFields());
    }

}
