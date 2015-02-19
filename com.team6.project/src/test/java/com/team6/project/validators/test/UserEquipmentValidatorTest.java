package com.team6.project.validators.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.Record;
import com.team6.project.entities.UserEquipment;
import com.team6.project.readers.UserEquipmentReader;
import com.team6.project.services.DataImportServiceFake;
import com.team6.project.validators.IValidator;
import com.team6.project.validators.UserEquipmentValidator;

public class UserEquipmentValidatorTest {
    
    private static DataImportServiceFake service;
    private static Record record;
    private static BaseData baseData;
    private static IValidator userEquipmentValidator;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        
        service = new DataImportServiceFake();
        record = new Record();
        baseData = new BaseData();
        userEquipmentValidator = new UserEquipmentValidator();
        @SuppressWarnings("rawtypes")
        Map<String, Map> map = new HashMap<>();
        Map<Integer, UserEquipment> ueMap = new HashMap<>();
        UserEquipment ue = new UserEquipment(100,"","","","","","","","");
        ueMap.put(ue.getKey(), ue);
        map.put(UserEquipmentReader.getName(), ueMap);
        service.setMap(map);
    }

    @Test
    public void eventCauseValidation_Valid() {
        record.setUserEquipment(100);
        assertTrue(userEquipmentValidator.isValid(record, baseData, service));
    }
   @Test
    public void eventCauseValidation_Null() {
       record.setUserEquipment(null);
       assertFalse(userEquipmentValidator.isValid(record, baseData, service));
    }
    
  
    @Test
    public void eventCauseValidation_NotValid() {
        record.setUserEquipment(6);
        assertFalse(userEquipmentValidator.isValid(record, baseData, service));
    }

}
