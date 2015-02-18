package com.team6.project.readers.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.BeforeClass;
import org.junit.Test;

import com.team6.project.entities.Record;
import com.team6.project.readers.BaseDataReader;
import com.team6.project.services.DataImportServiceFake;

public class BaseDataReaderTest {

    private static HSSFSheet sheet;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        DataImportServiceFake service = new DataImportServiceFake();
        HSSFWorkbook workBook = new HSSFWorkbook(new FileInputStream("src/test/resources/DITSampleDataset_SHORT.xls"));
        service.setWorkBook(workBook);
        sheet = service.getSheet(BaseDataReader.getName());
    }

    @Test
    public void readTest() {
        Record record = new Record();
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
        Record other = new BaseDataReader().read(sheet);
        assertTrue(record.equals(other));
                
    }

}
