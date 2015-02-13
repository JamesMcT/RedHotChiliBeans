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
    private int causeCode;
    @Id
    private int eventId;
    private String description;

    public EventCause() {
    }

    public EventCause(int causeCode, int eventId, String description) {
        super();
        this.causeCode = causeCode;
        this.eventId = eventId;
        this.description = description;
    }

    public int getCauseCode() {
        return causeCode;
    }

    public void setCauseCode(int causeCode) {
        this.causeCode = causeCode;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
