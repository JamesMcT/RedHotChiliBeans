package com.team6.project.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Cristiana FailureType table
 */
@Entity
public class FailureType implements Serializable {

	@Id
	private Integer failureCode;
	private String description;
	private static final long serialVersionUID = -1420241973884926828L;

	/**
     * 
     */
	public FailureType() {
	}

	/**
	 * 
	 * @param failureCode
	 * @param descrption
	 */
	public FailureType(Integer failureCode, String descrption) {
		super();
		this.failureCode = failureCode;
		this.description = descrption;
	}

	/**
 * 
 */
	public String toString() {
		return "Failure Code : " + failureCode + " Description : "
				+ description;
	}

	/**
	 * 
	 * @return
	 */
	public boolean hasRequiredFields() {
		if (failureCode != null) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getKey() {
		return failureCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((failureCode == null) ? 0 : failureCode.hashCode());
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
		FailureType other = (FailureType) obj;
		if (failureCode == null) {
			if (other.failureCode != null)
				return false;
		} else if (!failureCode.equals(other.failureCode))
			return false;
		return true;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getFailureCode() {
		return failureCode;
	}

	/**
	 * 
	 * @param failureCode
	 */
	public void setFailureCode(Integer failureCode) {
		this.failureCode = failureCode;
	}

	/**
	 * 
	 * @return
	 */
	public String getDescrption() {
		return description;
	}

	/**
	 * 
	 * @param descrption
	 */
	public void setDescription(String descrption) {
		this.description = descrption;
	}

}
