package com.team6.project.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * This class creates an Entity called FailureType which implements
 * Serializable.
 * 
 * <p>
 * It creates 2 columns called <i> failureCode </i> and <i>description </i>.
 * 
 * @author Cristiana
 */
@Entity
public class FailureType implements Serializable {

	@Id
	private Integer failureCode;
	private String description;
	private static final long serialVersionUID = -1420241973884926828L;

	/**
	 * Empty constructor
	 * 
	 */
	public FailureType() {
	}

	/**
	 * Constructor for FailureType
	 * 
	 * @param failureCode
	 * @param description
	 */
	public FailureType(Integer failureCode, String description) {
		super();
		this.failureCode = failureCode;
		this.description = description;
	}

	/**
	 * Overrides the toString() method. It overrides to return the failureCode
	 * and description.
	 * 
	 */
	public String toString() {
		return "Failure Code : " + failureCode + " Description : "
				+ description;
	}

	/**
	 * Checks whether failureCode is present.
	 * 
	 * @return true if has required field. otherwise false
	 */
	public boolean hasRequiredFields() {
		if (failureCode != null) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the key as an Integer
	 * 
	 * @return failureCode
	 */
	public Integer getKey() {
		return failureCode;
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
				+ ((failureCode == null) ? 0 : failureCode.hashCode());
		return result;
	}

	/**
	 * This overrides the equals method to compare failureCode
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
		FailureType other = (FailureType) obj;
		if (failureCode == null) {
			if (other.failureCode != null)
				return false;
		} else if (!failureCode.equals(other.failureCode))
			return false;
		return true;
	}

	/**
	 * Gets the failureCode as an Integer
	 * 
	 * @return failureCode
	 */
	public Integer getFailureCode() {
		return failureCode;
	}

	/**
	 * Sets the failureCode as an Integer
	 * 
	 * @param failureCode
	 */
	public void setFailureCode(Integer failureCode) {
		this.failureCode = failureCode;
	}

	/**
	 * Gets the description as a String
	 * 
	 * @return description
	 */
	public String getDescrption() {
		return description;
	}

	/**
	 * Sets the description as a String
	 * 
	 * @param descrption
	 */
	public void setDescription(String descrption) {
		this.description = descrption;
	}

}
