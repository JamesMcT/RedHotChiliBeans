package com.team6.project.entities.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.UserEquipment;

public class BaseDataTest {
    
    private static BaseData bd1;
    private static BaseData bd2;
    private static EventCause eventCause;
    private static FailureType failureType;
    private static OperatorCountry operatorCountry;
    private static UserEquipment userEquipment;

    @BeforeClass
    public static void setUp() throws Exception {
       setFirstBaseData();
    }
    
    @Before
    public void setUpBefore() throws Exception{
    	setSecondBaseData();
    }
    
    @Test
    public void getKeyTest_True(){
    	bd1.setId(1);
    	assertEquals(new Integer(1), bd1.getId());
    }
    
    @Test
    public void getKeyTest_False(){
    	bd1.setId(1);
    	assertNotEquals(new Integer(9), bd1.getId());
    }
    
    @Test
    public void equalsTrueTest(){
    	assertTrue(bd2.equals(bd1));
    }
        
    @Test
    public void equalsFalseTest_EventCause(){
    	EventCause ec = new EventCause(9,9,"New Description");
    	bd1.setEventCause(ec);
    	assertFalse(bd1.equals(bd2));
    }
    
    @Test
    public void equalsFalseTest_OperatorCountry(){
    	OperatorCountry oc= new OperatorCountry(9, 1, "Country", "Operator");
    	bd1.setOperatorCountry(oc);
    	assertFalse(bd1.equals(bd2));
    }
    
    @Test
    public void equalsFalseTest_FailureType(){
    	FailureType ft = new FailureType(2, "desc Failure Type");
    	bd1.setFailure(ft);
    	assertFalse(bd1.equals(bd2));
    }
    
    @Test
    public void equalsFalseTest_UserEquipment(){
    	UserEquipment ue = new UserEquipment(12, "a", "a", "a", "a", "a", "a", "a", "a");
    	bd1.setUserEquipment(ue);
    	assertFalse(bd1.equals(bd2));
    }
    
    @Test
    public void equalsFalseTest_Duration(){
    	bd1.setDuration(1001);
    	assertFalse(bd1.equals(bd2));
    }
    
    @Test
    public void equalsFalseTest_Imsi(){
    	BigInteger bi = new BigInteger("99");
    	bd1.setImsi(bi);
    	assertFalse(bd1.equals(bd2));
    }
    
    @Test
    public void equalsFalseTest_Hier321Id(){
    	BigInteger bi = new BigInteger("99");
    	bd1.setHier321Id(bi);
    	assertFalse(bd1.equals(bd2));
    }
    
    @Test
    public void equalsFalseTest_Hier32Id(){
    	BigInteger bi = new BigInteger("99");
    	bd1.setHier32Id(bi);
    	assertFalse(bd1.equals(bd2));
    }
    
    @Test
    public void equalsFalseTest_Hier3Id(){
    	BigInteger bi = new BigInteger("99");
    	bd1.setHier3Id(bi);
    	assertFalse(bd1.equals(bd2));
    }
    
    @Test
    public void equalsFalseTest_NeVersion(){
    	bd1.setNeVersion("3G");
    	assertFalse(bd1.equals(bd2));
    }
    
    @Test
    public void hashCode_Same() {
        assertEquals(bd1.hashCode(), bd2.hashCode());
    }
    
    @Test
    public void hashCode_NotSame() {
        bd1.setDuration(100);
        assertNotEquals(bd1.hashCode(), bd2.hashCode());
    }

    
    @Test
    public void equalsFalseTest_Date(){
	    sleep(100);
	    bd2.setDate(new Date());
	    assertFalse(bd1.equals(bd2));
    }
    
   
    private static void setFirstBaseData() {
       	BigInteger b = new BigInteger("1234");
    	operatorCountry= new OperatorCountry(1, 2, "Country", "Operator");
    	eventCause = new EventCause(1, 2, "desc Event Cause");
    	failureType = new FailureType(1, "desc Failure Type");
    	userEquipment = new UserEquipment(123, "a", "a", "a", "a", "a", "a", "a", "a");
    	
    	bd1 = new BaseData();
		bd1.setDate(new Date());
		bd1.setEventCause(eventCause);
		bd1.setCellId(4);
		bd1.setDuration(1000);
		bd1.setFailure(failureType);
		bd1.setHier321Id(b);
		bd1.setHier32Id(b);
		bd1.setHier3Id(b);
		bd1.setImsi(b);
		bd1.setOperatorCountry(operatorCountry);
		bd1.setNeVersion("12g");
		bd1.setUserEquipment(userEquipment);
		
	}

    private void setSecondBaseData() {
    	bd2 = new BaseData();
    	bd2.setDate(bd1.getDate());
    	bd2.setEventCause(bd1.getEventCause());
    	bd2.setCellId(bd1.getCellId());
    	bd2.setDuration(bd1.getDuration());
    	bd2.setFailure(bd1.getFailure());
    	bd2.setHier321Id(bd1.getHier321Id());
    	bd2.setHier32Id(bd1.getHier32Id());
    	bd2.setHier3Id(bd1.getHier3Id());
    	bd2.setImsi(bd1.getImsi());
    	bd2.setOperatorCountry(bd1.getOperatorCountry());
    	bd2.setNeVersion(bd1.getNeVersion());
    	bd2.setUserEquipment(bd1.getUserEquipment());
		
	}
    
    private void sleep(long ms){
    	try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
