package com.team6.project.validators.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.Record;
import com.team6.project.readers.FailureTypeReader;
import com.team6.project.services.DataImportServiceFake;
import com.team6.project.validators.FailureTypeValidator;
import com.team6.project.validators.IValidator;

public class FailureTypeValidatorTest {

    private static DataImportServiceFake service;
    private static Record record;
    private static BaseData baseData;
    private static IValidator failureTypeValidator;


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        service = new DataImportServiceFake();
        record = new Record();
        baseData = new BaseData();
        failureTypeValidator = new FailureTypeValidator();
        @SuppressWarnings("rawtypes")
        Map<String, Map> map = new HashMap<>();
        Map<Integer, FailureType> failureMap = new HashMap<>();
        FailureType failureType = new FailureType( 2, "description");
        failureMap.put(failureType.getKey(), failureType);
        map.put(FailureTypeReader.getName(), failureMap);
        service.setMap(map);
    }


    @Test
    public void eventCauseValidation_Valid() {
        record.setFailureType(2);
        assertTrue(failureTypeValidator.isValid(record, baseData, service));
    }
   @Test
    public void eventCauseValidation_Null() {
       record.setFailureType(null);
       assertFalse(failureTypeValidator.isValid(record, baseData, service));
    }
    
  
    @Test
    public void eventCauseValidation_NotValid() {
        record.setFailureType(10);
        assertFalse(failureTypeValidator.isValid(record, baseData, service));
    }

}
