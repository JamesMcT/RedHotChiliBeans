package com.team6.project.entities.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.team6.project.entities.Record;

public class RecordTest {
    private static Record rec1;
    private static Record rec2;
    private static Record rec3;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        setRecord();
    }

    @Test
    public void getKeyTest_True() {
        assertEquals(new Integer(rec1.getKey()), new Integer(1));
    }
    
    @Test
    public void getKeyTest_False() {
        assertNotEquals(new Integer(rec1.getKey()), new Integer(4));
    }

    @Test
    public void equalsTrueTest() {
        assertTrue(rec2.equals(rec3));
    }
    
    @Test
    public void equalsTrueTest2() {
        assertTrue(rec2.equals(rec2));
    }
    @Test
    public void equalsFalseTest_EventId() {
        assertFalse(rec1.equals(rec2));
    }
     
    private static void setRecord(){
        rec1 = new Record();
        rec1.setId(1);
        rec1.setDate(new Date());
        rec1.setCauseCode(1);
        rec1.setCellId(4);
        rec1.setDescription("");
        rec1.setDuration(1000);
        rec1.setEventId(200);
        rec1.setFailureType(10);
        rec1.setHier321Id(new BigInteger("1234567890"));
        rec1.setHier32Id(new BigInteger("1234567890"));
        rec1.setHier3Id(new BigInteger("1234567890"));
        rec1.setImsi(new BigInteger("1234567890"));
        rec1.setMcc(123);
        rec1.setMnc(321);
        rec1.setNeVersion("12g");
        rec1.setUserEquipment(12345678);
        
        rec2 = new Record();
        rec2.setDate(new Date());
        rec2.setCauseCode(3);
        rec2.setCellId(5);
        rec2.setDescription("");
        rec2.setDuration(1000);
        rec2.setEventId(90);
        rec2.setFailureType(10);
        rec2.setHier321Id(new BigInteger("1234567890"));
        rec2.setHier32Id(new BigInteger("1234567890"));
        rec2.setHier3Id(new BigInteger("1234567890"));
        rec2.setImsi(new BigInteger("1234567890"));
        rec2.setMcc(123);
        rec2.setMnc(321);
        rec2.setNeVersion("12g");
        rec2.setUserEquipment(12345678);
        
        rec3 = new Record();
        rec3.setDate(new Date());
        rec3.setCauseCode(3);
        rec3.setCellId(5);
        rec3.setDescription("");
        rec3.setDuration(1000);
        rec3.setEventId(90);
        rec3.setFailureType(10);
        rec3.setHier321Id(new BigInteger("1234567890"));
        rec3.setHier32Id(new BigInteger("1234567890"));
        rec3.setHier3Id(new BigInteger("1234567890"));
        rec3.setImsi(new BigInteger("1234567890"));
        rec3.setMcc(123);
        rec3.setMnc(321);
        rec3.setNeVersion("12g");
        rec3.setUserEquipment(12345678);
    }
}
