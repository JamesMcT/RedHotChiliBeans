package com.team6.project.readers.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.team6.project.entities.Record;
import com.team6.project.readers.BaseDataReader;
import com.team6.project.services.DataImportServiceFake;

public class BaseDataReaderTest {

    private static HSSFSheet sheet;
    private static Record record;
    private static BaseDataReader baseDataReader;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        DataImportServiceFake service = new DataImportServiceFake();
        HSSFWorkbook workBook = new HSSFWorkbook(new FileInputStream("src/test/resources/DITSampleDataset_SHORT.xls"));
        service.setWorkBook(workBook);
        sheet = service.getSheet(BaseDataReader.getName());
        record = new Record();
        baseDataReader = new BaseDataReader();
    }
    
    @Before
    public void setUp()throws Exception {
        String date = "11/01/2013 17:15:00"; 
        SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyyy hh:mm:ss"); 
        try {
            record.setDate(df.parse(date));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        record.setEventId(4097);
        record.setFailureType(1);
        record.setUserEquipment(21060800);
        record.setMcc(344);
        record.setMnc(930);
        record.setCellId(4);
        record.setDuration(1000);
        record.setCauseCode(13);
        record.setNeVersion("11B");
        record.setImsi(new BigInteger("344930000000011"));
        record.setHier3Id(new BigInteger("4809532081614990000"));
        record.setHier32Id(new BigInteger("8226896360947470000"));
        record.setHier321Id(new BigInteger("1150444940909480000"));
        
    }
    @Test
    public void readTest_AllFilled() {
        baseDataReader.setCurrentRow(1);
        Record other = baseDataReader.read(sheet);
        assertTrue(record.equals(other));
                
    }

    @Test
    public void readTest_EmptyDate() {
        baseDataReader.setCurrentRow(2);
        record.setDate(null);
        Record other = baseDataReader.read(sheet);
        assertTrue(record.equals(other));
        assertEquals(record.getDate(), null);
                
    }
    @Test
    public void readTest_EmptyeventId() {
        baseDataReader.setCurrentRow(3);
        record.setEventId(null);
        Record other = baseDataReader.read(sheet);
        assertTrue(record.equals(other));
        assertEquals(record.getEventId(), null);
    }
    @Test
    public void readTest_EmptyFailureType() {
        baseDataReader.setCurrentRow(4);
        record.setFailureType(null);
        Record other = baseDataReader.read(sheet);
        assertTrue(record.equals(other));
        assertEquals(record.getFailureType(), null);
    }

    @Test
    public void readTest_EmptyUE() {
        baseDataReader.setCurrentRow(5);
        record.setUserEquipment(null);
        Record other = baseDataReader.read(sheet);
        assertTrue(record.equals(other));
        assertEquals(record.getUserEquipment(), null);
    }
    
    @Test
    public void readTest_EmptyMCC() {
        baseDataReader.setCurrentRow(6);
        record.setMcc(null);
        Record other = baseDataReader.read(sheet);
        assertTrue(record.equals(other));
        assertEquals(record.getMcc(), null);
    }
    
    @Test
    public void readTest_EmptyMNC() {
        baseDataReader.setCurrentRow(7);
        record.setMnc(null);
        Record other = baseDataReader.read(sheet);
        assertTrue(record.equals(other));
        assertEquals(record.getMnc(), null);
    }
    
    @Test
    public void readTest_EmptyCellID() {
        baseDataReader.setCurrentRow(8);
        record.setCellId(null);
        Record other = baseDataReader.read(sheet);
        assertTrue(record.equals(other));
        assertEquals(record.getCellId(), null);
    }
    
    @Test
    public void readTest_EmptyDuration() {
        baseDataReader.setCurrentRow(9);
        record.setDuration(null);
        Record other = baseDataReader.read(sheet);
        assertTrue(record.equals(other));
        assertEquals(record.getDuration(), null);
    }
    @Test
    public void readTest_EmptyCauseCode() {
        baseDataReader.setCurrentRow(10);
        record.setCauseCode(null);
        Record other = baseDataReader.read(sheet);
        assertTrue(record.equals(other));
        assertEquals(record.getCauseCode(),null);
    }
    
    @Test
    public void readTest_EmptyNE() {
        baseDataReader.setCurrentRow(11);
        record.setNeVersion(null);
        Record other = baseDataReader.read(sheet);
        assertTrue(record.equals(other));
        assertEquals(record.getNeVersion(), null);
    }
    

    @Test
    public void readTest_EmptyImsi() {
        baseDataReader.setCurrentRow(12);
        record.setImsi(null);
        Record other = baseDataReader.read(sheet);
        assertTrue(record.equals(other));
        assertEquals(record.getImsi(),null);
    }
    
    @Test
    public void readTest_Emptyhier3() {
        baseDataReader.setCurrentRow(13);
        record.setHier3Id(null);
        Record other = baseDataReader.read(sheet);
        assertTrue(record.equals(other));
        assertEquals(record.getHier3Id(),null);
    }
    
    @Test
    public void readTest_Emptyhier32() {
        baseDataReader.setCurrentRow(14);
        record.setHier32Id(null);
        Record other = baseDataReader.read(sheet);
        assertTrue(record.equals(other));
        assertEquals(record.getHier32Id(),null);
    }

    @Test
    public void readTest_Emptyhier321() {
        baseDataReader.setCurrentRow(15);
        record.setHier321Id(null);
        Record other = baseDataReader.read(sheet);
        assertTrue(record.equals(other));
        assertEquals(record.getHier321Id(),null);
    }
}
