package com.team6.project.readers.test;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.BeforeClass;
import org.junit.Test;

import com.team6.project.entities.FailureType;
import com.team6.project.readers.FailureTypeReader;
import com.team6.project.services.DataImportServiceFake;

public class FailureTypeReaderTest {
    
    private static DataImportServiceFake service;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        service = new DataImportServiceFake();
        HSSFWorkbook workBook = new HSSFWorkbook(new FileInputStream("src/test/resources/DITSampleDataset_SHORT.xls"));
        service.setWorkBook(workBook);
        @SuppressWarnings({ "rawtypes", "unchecked" })
        Map<String, Map> map = new HashMap();
        Map<Integer, FailureType> failureTypeMap = new HashMap<>();
        map.put(FailureTypeReader.getName(), failureTypeMap);
        service.setMap(map);
    }

    @Test
    public void importFailureType() {
        Collection<FailureType> list = new ArrayList<>();
        list.add(new FailureType(0, "EMERGENCY"));
        list.add(new FailureType(1, "HIGH PRIORITY ACCESS"));
        list.add(new FailureType(2, "MT ACCESS"));
        list.add(new FailureType(3, "MO SIGNALLING"));
        list.add(new FailureType(4, "MO DATA"));
        FailureTypeReader reader = new FailureTypeReader();
        reader.processExcelFile(service);
        assertTrue(CollectionUtils.isEqualCollection(service.getMap(FailureTypeReader.getName()).values(), list));
    }

}
