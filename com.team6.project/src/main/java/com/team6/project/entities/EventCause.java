package com.team6.project.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.team6.project.entities.EventCausePK;

/**
 * This class creates an Entity called EventCause which implements Serializable
 * and uses the composite key called EventCausePK.
 * <p>
 * It creates 3 columns called <i> causeCode </i>,<i> eventId </i>, and <i>
 * description </i>
 * 
 * @author Cristiana Conti
 * 
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
	 * Empty constuctor for EventCause
	 * 
	 */
	public EventCause() {
	}

	/**
	 * The constructor for EventCause
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
	 * Checks whether causeCode and eventId are present.
	 * 
	 * @return true if has required fields. otherwise false
	 */
	public boolean hasRequiredFields() {
		if (causeCode != null && eventId != null) {
			return true;
		}
		return false;
	}

	/**
	 * Overrides the toString() method. It overrides to return the eventID
	 * causeCode and Description.
	 * 
	 */
	public String toString() {
		return "Event Id : " + eventId + " Cause code: " + causeCode
				+ " Description: " + description;
	}

	/**
	 * Gets the composite key of the EventCausePK
	 * 
	 * @return EventCausePK object with eventId and causeCode
	 */
	public EventCausePK getKey() {
		return new EventCausePK(eventId, causeCode);
	}

	/**
	 * This overrides the hashcode() method which uses 31 as a base
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((causeCode == null) ? 0 : causeCode.hashCode());
		result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
		return result;
	}

	/**
	 * This overrides the equals method comparing causeCode and eventId
	 * 
	 */
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
	 * Gets the CauseCode as an Integer
	 * 
	 * @return causeCode
	 */
	public Integer getCauseCode() {
		return causeCode;
	}

	/**
	 * Sets the CauseCode as an Integer
	 * 
	 * @param causeCode
	 */
	public void setCauseCode(Integer causeCode) {
		this.causeCode = causeCode;
	}

	/**
	 * Gets the EventId as an Integer
	 * 
	 * @return eventId
	 */
	public Integer getEventId() {
		return eventId;
	}

	/**
	 * Sets the EventId as an Integer
	 * 
	 * @param eventId
	 */
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	/**
	 * Gets the description as a String
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description as a String
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
