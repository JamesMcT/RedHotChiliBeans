package com.team6.project.entities;

/**
 * This class gives the response with the status and description
 * 
 * @author Cristiana Conti
 *
 */
public class Response {

	public enum Status {
		OK, ERROR, NOT_FOUND

	}

	private String description;
	private Status status;

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

	/**
	 * Gets the status as an Object
	 * 
	 * @return status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Sets the status as an Object
	 * 
	 * @param status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

}
