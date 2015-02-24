package com.team6.project.validators.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.EventCausePK;
import com.team6.project.entities.Record;
import com.team6.project.readers.EventCauseReader;
import com.team6.project.services.DataImportServiceFake;
import com.team6.project.validators.EventCauseValidator;
import com.team6.project.validators.IValidator;

public class EventCauseValidatorTest {
    
    private static DataImportServiceFake service;
    private static Record record;
    private static BaseData baseData;
    private static IValidator eventCauseValidator;


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        service = new DataImportServiceFake();
        record = new Record();
        baseData = new BaseData();
        eventCauseValidator = new EventCauseValidator();
        @SuppressWarnings("rawtypes")
        Map<String, Map> map = new HashMap<>();
        Map<EventCausePK, EventCause> eventCauseMap = new HashMap<>();
        EventCause eventCause = new EventCause(1, 2, "description");
        eventCauseMap.put(eventCause.getKey(), eventCause);
        map.put(EventCauseReader.getName(), eventCauseMap);
        service.setMap(map);
    }

    @Test
    public void eventCauseValidation_Valid() {
        record.setEventId(1);
        record.setCauseCode(2);
        assertTrue(eventCauseValidator.isValid(record, baseData, service));
    }
    @Test
    public void eventCauseValidation_NullEventId() {
        record.setEventId(null);
        record.setCauseCode(2);
        assertFalse(eventCauseValidator.isValid(record, baseData, service));
    }
    
    @Test
    public void eventCauseValidation_NullCauseCode() {
        record.setEventId(1);
        record.setCauseCode(null);
        assertFalse(eventCauseValidator.isValid(record, baseData, service));
    }

    @Test
    public void eventCauseValidation_Null() {
        record.setEventId(null);
        record.setCauseCode(null);
        assertFalse(eventCauseValidator.isValid(record, baseData, service));
    }
    
    @Test
    public void eventCauseValidation_NotValidEventId() {
        record.setEventId(3);
        record.setCauseCode(2);
        assertFalse(eventCauseValidator.isValid(record, baseData, service));
    }
    
    @Test
    public void eventCauseValidation_NotValidCauseCode() {
        record.setEventId(1);
        record.setCauseCode(5);
        assertFalse(eventCauseValidator.isValid(record, baseData, service));
    }
    @Test
    public void eventCauseValidation_NotValid() {
        record.setEventId(9);
        record.setCauseCode(5);
        assertFalse(eventCauseValidator.isValid(record, baseData, service));
    }

}
