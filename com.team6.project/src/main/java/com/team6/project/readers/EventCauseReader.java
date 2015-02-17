package com.team6.project.readers;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.team6.project.entities.EventCause;
import com.team6.project.entities.EventCausePK;
import com.team6.project.services.MapExcelInterface;

/**
 * Reads rows in sheet called Event-Cause Table.
 * Create the EventCause object.
 * If the object is not already in the appropriated map
 * it is added and written to the DB
 * 
 * @author Cristiana
 */
public class EventCauseReader extends Reader {

    private static final String NAME = "Event-Cause Table";

    // The name is not correct because the service is provided and not the excel
    // we could call that import or similar. We should have an interface
    // implemented by DataImportService
    // that make the reader class able to call only the method that it uses
    // without messing with the
    // DataImportService.
    @Override
    public void processExcelFile(MapExcelInterface service) {
        HSSFSheet sheet = service.getSheet(NAME);
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
                    service.getMap(NAME).put(pk, eventCause);
                    // persistence.persist(eventCause);
                }
                // It is already in the map
            } else {
                // Data corrupted write Log file
            }
            currentRow++;
        }

    }

    public static String getName() {
        return NAME;
    }

}
