package com.team6.project.entities.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

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
