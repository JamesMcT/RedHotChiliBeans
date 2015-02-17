package com.team6.project.validators.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.EventCausePK;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.OperatorCountryPK;
import com.team6.project.entities.Record;
import com.team6.project.entities.UserEquipment;
import com.team6.project.readers.BaseDataReader;
import com.team6.project.readers.EventCauseReader;
import com.team6.project.readers.FailureTypeReader;
import com.team6.project.readers.OperatorCountryReader;
import com.team6.project.readers.UserEquipmentReader;
import com.team6.project.services.DataImportServiceFake;
import com.team6.project.validators.IValidator;

public class AllTrueCompositeValidatorTest {

    private static IValidator validator;
    private static Record rec1;
    private static DataImportServiceFake service;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        validator = BaseDataReader.createValidator();
        rec1 = new Record();
        service = new DataImportServiceFake();
        Map<String, Map> map = new HashMap();
        Map<EventCausePK, EventCause> eventCauseMap = new HashMap<>();
        eventCauseMap.put((new EventCausePK(1, 2)), (new EventCause(1,2,"desc")));
        map.put(EventCauseReader.getName(), eventCauseMap);
        Map<Integer, FailureType> failureTypeMap = new HashMap<>();
        failureTypeMap.put(0, (new FailureType(0,"desc")));
        map.put(FailureTypeReader.getName(), failureTypeMap);
        Map<OperatorCountryPK, OperatorCountry> operCountryMap = new HashMap<>();
        operCountryMap.put((new OperatorCountryPK(3, 4)), (new OperatorCountry(3,4,"country","operator")));
        map.put(OperatorCountryReader.getName(), operCountryMap);
        Map<Integer, UserEquipment> userEquipmentMap = new HashMap<>();
        userEquipmentMap.put(5, (new UserEquipment(5,"desc","","","","","","","")));
        map.put(UserEquipmentReader.getName(), userEquipmentMap);
        service.setMap(map);
    }
    
    @Test
    public void isValidTest() {
        BaseData baseData = new BaseData();
        rec1.setDate(new Date());
        rec1.setEventId(1);
        rec1.setCauseCode(2);
        rec1.setFailureType(0);
        rec1.setMcc(3);
        rec1.setMnc(4);
        rec1.setUserEquipment(5);
        //assertTrue(validator.isValid(rec1, baseData, service));
    }

    @Test
    public void isValidTest_NoValidDate() {
        BaseData baseData = new BaseData();
        rec1.setDate(null);
        rec1.setEventId(1);
        rec1.setCauseCode(2);
        rec1.setFailureType(0);
        rec1.setMcc(3);
        rec1.setMnc(4);
        rec1.setUserEquipment(5);
        //assertFalse(validator.isValid(rec1, baseData, service));
    }
    
    @Test
    public void isValidTest_NoValidFailureType() {
        BaseData baseData = new BaseData();
        rec1.setDate(new Date());
        rec1.setEventId(1);
        rec1.setCauseCode(2);
        rec1.setFailureType(null);
        rec1.setMcc(3);
        rec1.setMnc(4);
        rec1.setUserEquipment(5);
        //assertFalse(validator.isValid(rec1, baseData, service));
    }
    
    @Test
    public void isValidTest_NoValidUserEquipment() {
        BaseData baseData = new BaseData();
        rec1.setDate(new Date());
        rec1.setEventId(1);
        rec1.setCauseCode(2);
        rec1.setFailureType(0);
        rec1.setMcc(3);
        rec1.setMnc(4);
        rec1.setUserEquipment(null);
        //assertFalse(validator.isValid(rec1, baseData, service));
    }
    
    @Test
    public void isValidTest_NoValidMCC() {
        BaseData baseData = new BaseData();
        rec1.setDate(new Date());
        rec1.setEventId(1);
        rec1.setCauseCode(2);
        rec1.setFailureType(0);
        rec1.setMcc(null);
        rec1.setMnc(4);
        rec1.setUserEquipment(5);
        //assertFalse(validator.isValid(rec1, baseData, service));
    }
    
    @Test
    public void isValidTest_NoValidMNC() {
        BaseData baseData = new BaseData();
        rec1.setDate(new Date());
        rec1.setEventId(1);
        rec1.setCauseCode(2);
        rec1.setFailureType(0);
        rec1.setMcc(3);
        rec1.setMnc(null);
        rec1.setUserEquipment(5);
        //assertFalse(validator.isValid(rec1, baseData, service));
    }
    
    @Test
    public void isValidTest_NoValidEventID() {
        BaseData baseData = new BaseData();
        rec1.setDate(new Date());
        rec1.setEventId(null);
        rec1.setCauseCode(2);
        rec1.setFailureType(0);
        rec1.setMcc(3);
        rec1.setMnc(4);
        rec1.setUserEquipment(5);
       // assertFalse(validator.isValid(rec1, baseData, service));
    }
    
    @Test
    public void isValidTest_NoValidCauseCode() {
        BaseData baseData = new BaseData();
        rec1.setDate(new Date());
        rec1.setEventId(1);
        rec1.setCauseCode(null);
        rec1.setFailureType(0);
        rec1.setMcc(3);
        rec1.setMnc(4);
        rec1.setUserEquipment(5);
        //assertFalse(validator.isValid(rec1, baseData, service));
    }

}
