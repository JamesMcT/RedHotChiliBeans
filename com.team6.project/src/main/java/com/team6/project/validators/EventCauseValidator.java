package com.team6.project.validators;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.EventCausePK;
import com.team6.project.entities.Record;
import com.team6.project.readers.EventCauseReader;
import com.team6.project.services.MapExcelInterface;

/**
 * Validates the EventId and the CauseCode to guarantee the FK satisfied 
 * @author Cristiana
 */
public class EventCauseValidator implements IValidator {

    @Override
    public boolean isValid(Record record, BaseData baseData, MapExcelInterface service) {
        EventCausePK pk = new EventCausePK(record.getEventId(), record.getCauseCode());
        //If we have service.getEventCauseMap it is not necessary to cast
        EventCause ec = (EventCause)service.getMap(EventCauseReader.getName()).get(pk);
        if(ec != null){
            baseData.setEventCause(ec);
            return true;
        }
  
        record.setDescription("Not valid Event Id / Cause Code");
        return false;
    }

}
