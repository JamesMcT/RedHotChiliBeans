package com.team6.project.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.team6.project.entities.EventCausePK;


/**
 * @author Cristiana 
 * 
 * EventCause table
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

    /**
     * 
     */
    public EventCause() {
    }

    /**
     * 
     * @param eventId
     * @param causeCode
     * @param description
     */
    public EventCause(Integer eventId, Integer causeCode, String description) {
        super();
        this.causeCode = causeCode;
        this.eventId = eventId;
        this.description = description;
    }

    /**
     * 
     * @return
     */
    public boolean hasRequiredFields() {
        if (causeCode != null && eventId != null) {
            return true;
        }
        return false;
    }

    /**
     * 
     */
    public String toString() {
        return "Event Id : " + eventId + " Cause code: " + causeCode
                + " Description: " + description;
    }
    
    /**
     * 
     * @return
     */
    public EventCausePK getKey(){
        return new EventCausePK(eventId, causeCode);
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
        EventCause other = (EventCause) obj;
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

    /**
     * 
     * @return
     */
    public Integer getCauseCode() {
        return causeCode;
    }

    /**
     * 
     * @param causeCode
     */
    public void setCauseCode(Integer causeCode) {
        this.causeCode = causeCode;
    }

    /**
     * 
     * @return
     */
    public Integer getEventId() {
        return eventId;
    }

    /**
     * 
     * @param eventId
     */
    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    /**
     * 
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    

}
