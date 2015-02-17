package com.team6.project.entities.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.team6.project.entities.FailureType;

public class FailureTypeTest {
    
    private static FailureType ft1;
    private static FailureType ft2;
    private static FailureType ft3;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        ft1 = new FailureType(null, "description");
        ft2 = new FailureType(1, "description");
        ft3 = new FailureType(2, null);
    }
    
    @Test
    public void getKeyTest_True() {
        assertEquals(ft2.getKey(), new Integer(1));
    }
    
    @Test
    public void getKeyTest_False() {
        assertNotEquals(ft2.getKey(), new Integer(2));
    }

    @Test
    public void toStringTest() {
        assertEquals(ft2.toString(), "Failure Code : 1 Description : description");
    }
    
    @Test
    public void equalsTrueTest() {
        FailureType ftOther = new FailureType(1, "description");
        assertTrue(ft2.equals(ftOther));
    }
    @Test
    public void equalsFalseTest_DifType() {
        FailureType ftOther = new FailureType(2, null);
        assertFalse(ft2.equals(ftOther));
    }
    @Test
    public void equalsFalseTest_NullType() {
        FailureType ftOther = new FailureType(null, null);
        assertFalse(ft2.equals(ftOther));
    }
    
    @Test
    public void hasRequiredFieldsTest_NoFailureType()  {
        assertFalse(ft1.hasRequiredFields());
    }
    @Test
    public void hasRequiredFieldsTest()  {
        assertTrue(ft2.hasRequiredFields());
    }
    @Test
    public void hasRequiredFieldsTest_NoDescription()  {
        assertTrue(ft3.hasRequiredFields());
    }

}
