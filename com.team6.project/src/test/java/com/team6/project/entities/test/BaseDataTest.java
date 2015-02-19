package com.team6.project.entities.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.team6.project.entities.BaseData;

public class BaseDataTest {
    
    private BaseData bd;

    @Before
    public void setUp() throws Exception {
        bd = new BaseData();
    }

    @Test
    public void getKey_Valid() {
        bd.setId(10);
        assertEquals(bd.getKey(),new Integer(10));
    }
    
    @Test
    public void getKey_NotValid() {
        bd.setId(5);
        assertNotEquals(bd.getKey(),new Integer(10));
    }

}
