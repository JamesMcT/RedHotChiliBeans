package com.team6.project.entities.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.team6.project.entities.OperatorCountry;

public class OperatorCountryTest {

    private static OperatorCountry oc1;
    private static OperatorCountry oc2;
    private static OperatorCountry oc3;
    private static OperatorCountry oc4;
    private static OperatorCountry oc5;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        oc1 = new OperatorCountry(null, null, "country", "operator");
        oc2 = new OperatorCountry(null, 1, "country", "operator");
        oc3 = new OperatorCountry(1, null, "country", "operator");
        oc4 = new OperatorCountry(1, 1, "country", "operator");
        oc5 = new OperatorCountry(1, 1, null, null);
    }

    @Test
    public void hasRequiredFieldsTest_NoBoth() {
        assertFalse(oc1.hasRequiredFields());
    }

    @Test
    public void hasRequiredFieldsTest_NoMcc() {
        assertFalse(oc2.hasRequiredFields());
    }

    @Test
    public void hasRequiredFieldsTest_NoMnc() {
        assertFalse(oc3.hasRequiredFields());
    }

    @Test
    public void hasRequiredFieldsTest() {
        assertTrue(oc4.hasRequiredFields());
    }

    @Test
    public void hasRequiredFieldsTest_NoDescription() {
        assertTrue(oc5.hasRequiredFields());
    }
}
