package com.team6.project.dao.jpa.test;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Date;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.UserEquipment;


@RunWith(Arquillian.class)
public class JPABaseDataDAOTest extends JPADAOTest{

    private BaseData baseData;
    private EventCause eventCause;
    private FailureType failureType;
    private OperatorCountry operatorCountry;
    private UserEquipment userEquipment;
   
    @Before
    public void populateDB() throws Exception{
        createBaseDate();
        clear();
        insertData();
    }
    
    @Test
    public void test(){
        BaseData bd = baseDataDAO.getBaseDataByKey(baseData.getId());
        assertEquals(bd, baseData);	// Real test which should be run.
    }
    
   /* @Test
    public void test_getDistinctEventByTac() {
        List<Object[]> eventCauseCol = (List<Object[]>) baseDataDAO.getDistinctEventByTac(123);
        System.out.println(eventCauseCol.get(0)[0] +" and ......"+eventCauseCol.get(0)[1]);
        assertFalse(eventCauseCol.isEmpty());
    }*/
    
        
    private void insertData() throws Exception {
        operatorCountryDAO.addOperatorCountry(operatorCountry);
        eventCauseDAO.addEventCauseData(eventCause);
        failureTypeDAO.addFailureType(failureType);
        userEquipmentDAO.addUserEquipment(userEquipment);
    
        baseDataDAO.addBaseData(baseData);
    }
    
    private void clear() throws Exception {
        baseDataDAO.deleteBaseData(baseData);
        operatorCountryDAO.deleteOperatorCountry(operatorCountry);
        eventCauseDAO.deleteEventCause(eventCause);
        failureTypeDAO.deleteFailureType(failureType);
        userEquipmentDAO.deleteUserEquipment(userEquipment);
      
    }
    
    private void createBaseDate(){
        
        Date date = new Date();
        BigInteger b = new BigInteger("1234");
        operatorCountry= new OperatorCountry(1, 2, "Country", "Operator");
        eventCause = new EventCause(1, 2, "desc Event Cause");
        failureType = new FailureType(1, "desc Failure Type");
        userEquipment = new UserEquipment(123, "a", "a", "a", "a", "a", "a", "a", "a");
        
        baseData = new BaseData();
        baseData.setDate(date);
        baseData.setEventCause(eventCause);
        baseData.setCellId(4);
        baseData.setDuration(1000);
        baseData.setFailure(failureType);
        baseData.setHier321Id(b);
        baseData.setHier32Id(b);
        baseData.setHier3Id(b);
        baseData.setImsi(b);
        baseData.setOperatorCountry(operatorCountry);
        baseData.setNeVersion("12g");
        baseData.setUserEquipment(userEquipment);
    }
}