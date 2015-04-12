package com.team6.project.entities.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.team6.project.entities.FailureType;

public class FailureTypeTest {
    
   
    private static FailureType ft;

    @Before
    public void setUpBeforeClass() throws Exception {
        ft = new FailureType(1, "description");
    }
    
    @Test
    public void getKeyTest_True() {
        assertEquals(ft.getKey(), new Integer(1));
    }
    
    @Test
    public void getKeyTest_False() {
        assertNotEquals(ft.getKey(), new Integer(2));
    }

    @Test
    public void toStringTest() {
        assertEquals(ft.toString(), "Failure Code : 1 Description : description");
    }
    
    @Test
    public void equalsTrueTest() {
        FailureType ftOther = new FailureType(1, "description");
        assertTrue(ft.equals(ftOther));
        assertTrue(ft.hashCode() == ftOther.hashCode());
    }
    @Test
    public void equalsFalseTest_DifType() {
        FailureType ftOther = new FailureType(2, null);
        assertFalse(ft.equals(ftOther));
        assertFalse(ft.hashCode() == ftOther.hashCode());
    }
    @Test
    public void equalsFalseTest_NullType() {
        FailureType ftOther = new FailureType(null, null);
        assertFalse(ft.equals(ftOther));
        assertFalse(ft.hashCode() == ftOther.hashCode());
    }
    
    @Test
    public void hasRequiredFieldsTest_NoFailureType()  {
        ft.setFailureCode(null);
        assertFalse(ft.hasRequiredFields());
    }
    @Test
    public void hasRequiredFieldsTest()  {
        assertTrue(ft.hasRequiredFields());
    }
    @Test
    public void hasRequiredFieldsTest_NoDescription()  {
        ft.setDescription(null);
        assertTrue(ft.hasRequiredFields());
    }

}
