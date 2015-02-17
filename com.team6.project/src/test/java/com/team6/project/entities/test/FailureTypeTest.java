package com.team6.project.entities.test;

import static org.junit.Assert.assertFalse;
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
        ft1 = new FailureType(null, "descrption");
        ft2 = new FailureType(1, "descrption");
        ft3 = new FailureType(2, null);
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
