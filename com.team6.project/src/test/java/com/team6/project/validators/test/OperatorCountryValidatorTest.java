package com.team6.project.validators.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.OperatorCountryPK;
import com.team6.project.entities.Record;
import com.team6.project.readers.OperatorCountryReader;
import com.team6.project.services.DataImportServiceFake;
import com.team6.project.validators.IValidator;
import com.team6.project.validators.OperatorCountryValidator;

public class OperatorCountryValidatorTest {
    
    private static DataImportServiceFake service;
    private static Record record;
    private static BaseData baseData;
    private static IValidator operatorCountryValidator;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        service = new DataImportServiceFake();
        record = new Record();
        baseData = new BaseData();
        operatorCountryValidator = new OperatorCountryValidator();
        Map<String, Map> map = new HashMap();
        Map<OperatorCountryPK,OperatorCountry> operatorCountryMap = new HashMap<>();
        OperatorCountry oc = new OperatorCountry(22,33,"country","operator");
        operatorCountryMap.put(oc.getKey(), oc);
        map.put(OperatorCountryReader.getName(), operatorCountryMap);
        service.setMap(map);
    }

    @Test
    public void operatorCountryValidation_Valid() {
        record.setMcc(22);
        record.setMnc(33);
        assertTrue(operatorCountryValidator.isValid(record, baseData, service));
    }
    @Test
    public void operatorCountryValidation_NullCountry() {
        record.setMcc(null);
        record.setMnc(33);
        assertFalse(operatorCountryValidator.isValid(record, baseData, service));
    }
    
    @Test
    public void operatorCountryValidation_NullOperator() {
        record.setMcc(22);
        record.setMnc(null);
        assertFalse(operatorCountryValidator.isValid(record, baseData, service));
    }

    @Test
    public void operatorCountryValidation_Null() {
        record.setMcc(null);
        record.setMnc(null);
        assertFalse(operatorCountryValidator.isValid(record, baseData, service));
    }
    
    @Test
    public void operatorCountryValidation_NotValidEventId() {
        record.setMcc(3);
        record.setMnc(33);
        assertFalse(operatorCountryValidator.isValid(record, baseData, service));
    }
    
    @Test
    public void operatorCountryValidation_NotValidCauseCode() {
        record.setMcc(22);
        record.setMnc(5);
        assertFalse(operatorCountryValidator.isValid(record, baseData, service));
    }
    @Test
    public void operatorCountryValidation_NotValid() {
        record.setMcc(9);
        record.setMnc(5);
        assertFalse(operatorCountryValidator.isValid(record, baseData, service));
    }

}
