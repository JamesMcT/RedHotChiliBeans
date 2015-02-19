
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

import com.team6.project.entities.UserEquipment;
import com.team6.project.readers.UserEquipmentReader;
import com.team6.project.services.DataImportServiceFake;

public class UserEquipmentReaderTest {

    private static DataImportServiceFake service;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        service = new DataImportServiceFake();
        HSSFWorkbook workBook = new HSSFWorkbook(new FileInputStream("src/test/resources/DITSampleDataset_SHORT.xls"));
        service.setWorkBook(workBook);
        @SuppressWarnings("rawtypes")
        Map<String, Map> map = new HashMap<>();
        Map<Integer,UserEquipment> userEquipmentMap = new HashMap<>();
        map.put(UserEquipmentReader.getName(), userEquipmentMap);
        service.setMap(map);
    }

    @Test
    public void test() {
        Collection<UserEquipment> list = new ArrayList<>();
        list.add(new UserEquipment(21060800,"VEA3","S.A.R.L. B  & B International", "GSM 1800, GSM 900","VEA3","S.A.R.L. B  & B International",null ,null,null));
        UserEquipmentReader reader = new UserEquipmentReader();
        reader.processExcelFile(service);
        assertTrue(CollectionUtils.isEqualCollection(service.getMap(UserEquipmentReader.getName()).values(), list));
    }

}
