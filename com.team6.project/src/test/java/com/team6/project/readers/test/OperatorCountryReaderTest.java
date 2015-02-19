
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

import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.OperatorCountryPK;
import com.team6.project.readers.OperatorCountryReader;
import com.team6.project.services.DataImportServiceFake;

public class OperatorCountryReaderTest {
    
    private static DataImportServiceFake service;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        service = new DataImportServiceFake();
        HSSFWorkbook workBook = new HSSFWorkbook(new FileInputStream("src/test/resources/DITSampleDataset_SHORT.xls"));
        service.setWorkBook(workBook);
        @SuppressWarnings("rawtypes")
        Map<String, Map> map = new HashMap<>();
        Map<OperatorCountryPK,OperatorCountry> operatorCountryMap = new HashMap<>();
        map.put(OperatorCountryReader.getName(), operatorCountryMap);
        service.setMap(map);
    }

    @Test
    public void test() {
        Collection<OperatorCountry> list = new ArrayList<>();
        list.add(new OperatorCountry(340,2, "Guadeloupe-France", "Outremer Telecom GP"));
        list.add(new OperatorCountry(344, 30, "Antigua and Barbuda","APUA PCS AG"));
        list.add(new OperatorCountry(344, 930, "Antigua and Barbuda","AT&T Wireless-Antigua AG"));

        OperatorCountryReader reader = new OperatorCountryReader();
        reader.processExcelFile(service);
        assertTrue(CollectionUtils.isEqualCollection(service.getMap(OperatorCountryReader.getName()).values(), list));
    }

}
