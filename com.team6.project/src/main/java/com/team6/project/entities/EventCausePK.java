package com.team6.project.entities;

import java.io.Serializable;

/**
 * @author Cristiana Additional class for implementing composite key
 */
public class EventCausePK implements Serializable {

    private static final long serialVersionUID = 679883259119743614L;
    private int causeCode;
    private int eventId;

    public EventCausePK() {
    }

    public EventCausePK(int causeCode, int eventId) {
        super();
        this.causeCode = causeCode;
        this.eventId = eventId;
    }
    
    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + causeCode;
        result = prime * result + eventId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EventCausePK other = (EventCausePK) obj;
        if (causeCode != other.causeCode)
            return false;
        if (eventId != other.eventId)
            return false;
        return true;
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

}
