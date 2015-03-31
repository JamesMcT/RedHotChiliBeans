package com.team6.project.entities;

import java.io.Serializable;

/**
 * This class implements the composite key for EventCause
 * 
 * 
 * @author Cristiana
 */
public class EventCausePK implements Serializable {

	private static final long serialVersionUID = 679883259119743614L;
	private Integer causeCode;
	private Integer eventId;

	/**
	 * This is the empty constructor for EventCausePK
	 * 
	 */
	public EventCausePK() {
	}

	/**
	 * This is the constructor for EventCausePK
	 * 
	 * @param eventId
	 * @param causeCode
	 */
	public EventCausePK(Integer eventId, Integer causeCode) {
		super();
		this.causeCode = causeCode;
		this.eventId = eventId;
	}

	/**
	 * Overrides the toString() method. It overrides to return the eventID and
	 * causeCode.
	 * 
	 */
	public String toString() {
		return "Event Id : " + eventId + " Cause code: " + causeCode;
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

	/**
	 * Gets the causeCode as an Integer
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

}
