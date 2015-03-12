package com.team6.project.readers;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.EventCausePK;
import com.team6.project.services.DataImportServiceLocal;

/**
 * Reads rows in sheet called Event-Cause Table. Create the EventCause object.
 * If the object is not already in the appropriated map it is added and written
 * to the DB
 * 
 * @author Cristiana Conti
 * @author Eoin Kernan
 */
public class EventCauseReader extends Reader {

    private static final String NAME = "Event-Cause Table";
  
    @SuppressWarnings("unchecked")
    @Override
    public void processExcelFile(DataImportServiceLocal service) {

        HSSFSheet sheet = service.getSheet(NAME);
        List<EventCause> dataList = new ArrayList<EventCause>();
        
        while (currentRow <= sheet.getLastRowNum()) {
            HSSFRow row = sheet.getRow(currentRow);
            EventCause eventCause = new EventCause();
            eventCause.setCauseCode(getIntegerFromCell(row.getCell(0)));
            eventCause.setEventId(getIntegerFromCell(row.getCell(1)));
            eventCause.setDescription(getStringFromCell(row.getCell(2)));
            EventCausePK pk = new EventCausePK(eventCause.getEventId(),
                                               eventCause.getCauseCode());
            if (eventCause.hasRequiredFields()) {
                if (!service.getMap(NAME).containsKey(pk)) {
                    readerLogger.info("In sheet " + NAME + " row number "
                            + row.getRowNum() +" not in map. Writing on DB as well....");
                    service.getMap(NAME).put(pk, eventCause);
                    dataList.add(eventCause);
                    //service.getPersistenceService().persistEventCause(eventCause);
                } else {
                    readerLogger.info("In sheet " + NAME + " row number "
                            + row.getRowNum() + " already in memory");
                }
            } else {
                readerLogger.warn("In sheet " + NAME + " row number "
                        + row.getRowNum() + " primary key not valued properly");
            }
            currentRow++;
        }
        
        if(dataList.size()>0){
        	service.getPersistenceService().persistEventCauseCollection(dataList);
        }
        currentRow = FIRSTROW;

    }

    /**
     * 
     * @return
     */
    public static String getName() {
        return NAME;
    }

}
