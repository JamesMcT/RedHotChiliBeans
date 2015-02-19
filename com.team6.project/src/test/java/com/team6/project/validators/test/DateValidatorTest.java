package com.team6.project.validators.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.BeforeClass;
import org.junit.Test;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.Record;
import com.team6.project.services.DataImportServiceFake;
import com.team6.project.services.DataImportServiceLocal;
import com.team6.project.validators.DateValidator;
import com.team6.project.validators.IValidator;

public class DateValidatorTest {

    private static DataImportServiceLocal service;
    private static Record record;
    private static BaseData baseData;
    private static IValidator dateValidator;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        service = new DataImportServiceFake();
        record = new Record();
        baseData = new BaseData();
        dateValidator = new DateValidator();
    }

    @Test
    public void dateValidation_Valid() {
        record.setDate(new Date());
        assertTrue(dateValidator.isValid(record, baseData, service));
    }

    @Test
    public void dateValidation_Null() {
        record.setDate(null);
        assertFalse(dateValidator.isValid(record, baseData, service));
    }

    @Test
    public void dateValidation_Future() {
        Date date = new Date();
        DateTime dtOrg = new DateTime(date);
        DateTime dtPlusOne = dtOrg.plusDays(1);
        record.setDate(dtPlusOne.toDate());
        assertFalse(dateValidator.isValid(record, baseData, service));
    }

}
