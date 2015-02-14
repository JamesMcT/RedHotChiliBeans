package com.team6.project.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * @author Cristiana EventCause table
 */

@Entity
@IdClass(EventCausePK.class)
public class EventCause implements Serializable {

    private static final long serialVersionUID = -7885704687060461631L;
    @Id
    private Integer causeCode;
    @Id
    private Integer eventId;
    private String description;

    public EventCause() {
    }

    public EventCause(Integer eventId, Integer causeCode, String description) {
        super();
        this.causeCode = causeCode;
        this.eventId = eventId;
        this.description = description;
    }
    
    public boolean hasRequiredFields(){
        if(causeCode != null && eventId != null){
            return true;
        }
        return false;
    }

    public Integer getCauseCode() {
        return causeCode;
    }

    public void setCauseCode(Integer causeCode) {
        this.causeCode = causeCode;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
