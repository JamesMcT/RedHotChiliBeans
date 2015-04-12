package com.team6.project.entities.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.OperatorCountryPK;

public class OperatorCountryTest {

    
    private static OperatorCountry oc;
    

    @Before
    public void setUpBeforeClass() throws Exception {
        oc = new OperatorCountry(1, 2, "country", "operator");
    }

    @Test
    public void getKeyTest_True() {
        assertEquals(oc.getKey(), new OperatorCountryPK(1,2));
    }
    
    @Test
    public void getKeyTest_False() {
        assertNotEquals(oc.getKey(), new OperatorCountryPK(2,1));
    }
    
    @Test
    public void toStringTest() {
        assertEquals(oc.toString(), "MCC : 1 MNC : 2 Operator : operator Country : country");
    }
    
    @Test
    public void equalsTrueTest() {
        OperatorCountry other = new OperatorCountry(1, 2, "country", "operator");
        assertTrue(oc.equals(other));
    }
    @Test
    public void equalsFalseTest_NoMCC() {
        OperatorCountry other = new OperatorCountry(null, 2, "country", "operator");
        assertFalse(oc.equals(other));
    }
    @Test
    public void equalsFalseTest_NoMNC() {
        OperatorCountry other = new OperatorCountry(1, null, "country", "operator");
        assertFalse(oc.equals(other));
    }
    
    @Test
    public void hasRequiredFieldsTest_NoBoth() {
        oc.setMcc(null);
        oc.setMnc(null);
        assertFalse(oc.hasRequiredFields());
    }

    @Test
    public void hasRequiredFieldsTest_NoMcc() {
        oc.setMcc(null);
        assertFalse(oc.hasRequiredFields());
    }

    @Test
    public void hasRequiredFieldsTest_NoMnc() {
        oc.setMnc(null);
        assertFalse(oc.hasRequiredFields());
    }

    @Test
    public void hasRequiredFieldsTest() {
        assertTrue(oc.hasRequiredFields());
    }

    @Test
    public void hasRequiredFieldsTest_NoDescription() {
        oc.setOperator(null);
        oc.setCountry(null);
        assertTrue(oc.hasRequiredFields());
    }
}
