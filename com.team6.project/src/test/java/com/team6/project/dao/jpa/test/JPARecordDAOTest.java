package com.team6.project.dao.jpa.test;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Date;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.team6.project.entities.Record;

@RunWith(Arquillian.class)
public class JPARecordDAOTest extends JPADAOTest{

    private Record record;

    @Before
    public void preparePersistenceTest() throws Exception {
        setRecord();
        clear();
        insertData();
    }

    @Test
    public void test() {
        Record rec = recordDAO.getRecordByKey(record.getKey());
        assertEquals(rec, record);
    }

    private void insertData() throws Exception {
        recordDAO.addRecord(record);
    }

    private void clear() throws Exception {
        recordDAO.deleteRecord(record);
    }

    private void setRecord() {
        record = new Record();
        record.setDate(new Date());
        record.setCauseCode(1);
        record.setCellId(4);
        record.setDescription("");
        record.setDuration(1000);
        record.setEventId(200);
        record.setFailureType(10);
        record.setHier321Id(new BigInteger("1234567890"));
        record.setHier32Id(new BigInteger("1234567890"));
        record.setHier3Id(new BigInteger("1234567890"));
        record.setImsi(new BigInteger("1234567890"));
        record.setMcc(123);
        record.setMnc(321);
        record.setNeVersion("12g");
        record.setUserEquipment(12345678);
    }

}
