package com.team6.project.readers.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.BeforeClass;
import org.junit.Test;

import com.team6.project.entities.EventCause;
import com.team6.project.entities.EventCausePK;
import com.team6.project.readers.EventCauseReader;
import com.team6.project.services.DataImportServiceFake;

public class eventCauseReaderTest {
    
    private static DataImportServiceFake service;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        service = new DataImportServiceFake();
        HSSFWorkbook workBook = new HSSFWorkbook(new FileInputStream("src/test/resources/DITSampleDataset_SHORT.xls"));
        service.setWorkBook(workBook);
        Map<String, Map> map = new HashMap();
        Map<EventCausePK, EventCause> eventCauseMap = new HashMap<>();
        map.put(EventCauseReader.getName(), eventCauseMap);
        service.setMap(map);
    }

    @Test
    public void importEventCause() {
        Collection<EventCause> list = new ArrayList<EventCause>();
        list.add(new EventCause(4106, 1, "INITIAL CTXT SETUP-UNSPECIFIED"));
        list.add(new EventCause(4125, 2, "UE CTXT RELEASE-AUTHENTICATION FAILURE"));
        list.add(new EventCause(4125, 1, "UE CTXT RELEASE-NORMAL RELEASE"));
        list.add(new EventCause(4097, 1, "RRC CONN SETUP-UNSPECIFIED"));
        EventCauseReader reader = new EventCauseReader();
        reader.processExcelFile(service);
        assertTrue(CollectionUtils.isEqualCollection(service.getMap(EventCauseReader.getName()).values(), list));
        
    }

}
