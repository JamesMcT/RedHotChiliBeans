package com.team6.project.entities.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.team6.project.entities.OperatorCountryPK;

public class OperatorCountryPKTest {

  
    private static OperatorCountryPK ocPK;


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        ocPK = new OperatorCountryPK(1, 2);
    }

     
    @Test
    public void toStringTest() {
        assertEquals(ocPK.toString(), "MCC : 1 MNC : 2");
    }
    
    @Test
    public void equalsTrueTest() {
        OperatorCountryPK other = new OperatorCountryPK(1, 2);
        assertTrue(ocPK.equals(other));
    }
    @Test
    public void equalsFalseTest_NoMCC() {
        OperatorCountryPK other = new OperatorCountryPK(null, 2);
        assertFalse(other.equals(ocPK));
    }
    @Test
    public void equalsFalseTest_NoMNC() {
        OperatorCountryPK other = new OperatorCountryPK(1, null);
        assertFalse(other.equals(ocPK));
    }
    
    
}
