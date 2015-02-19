
package com.team6.project.entities;

import java.io.Serializable;

/**
 * @author Cristiana Additional class for implementing composite key
 */
public class EventCausePK implements Serializable {

    private static final long serialVersionUID = 679883259119743614L;
    private Integer causeCode;
    private Integer eventId;

    public EventCausePK() {
    }

    public EventCausePK(Integer eventId, Integer causeCode) {
        super();
        this.causeCode = causeCode;
        this.eventId = eventId;
    }

    public String toString() {
        return "Event Id : " + eventId + " Cause code: " + causeCode;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((causeCode == null) ? 0 : causeCode.hashCode());
        result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
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
        if (causeCode == null) {
            if (other.causeCode != null)
                return false;
        } else if (!causeCode.equals(other.causeCode))
            return false;
        if (eventId == null) {
            if (other.eventId != null)
                return false;
        } else if (!eventId.equals(other.eventId))
            return false;
        return true;
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
    
    

}

