package com.team6.project.entities;

import java.io.Serializable;

/**
 * This class implements the composite key for OperatorCountry
 * 
 * @author Cristiana
 */
public class OperatorCountryPK implements Serializable {

	private static final long serialVersionUID = -8462976575139159548L;
	private Integer mcc;
	private Integer mnc;

	/**
	 * Empty constructor
	 * 
	 */
	public OperatorCountryPK() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Overrides the toString() method. It overrides to return the mcc and mnc.
	 * 
	 * 
	 */
	public String toString() {
		return "MCC : " + mcc + " MNC : " + mnc;
	}

	/**
	 * This is the constructor for OperatorCountryPK
	 * 
	 * @param mcc
	 * @param mnc
	 */
	public OperatorCountryPK(Integer mcc, Integer mnc) {
		super();
		this.mcc = mcc;
		this.mnc = mnc;
	}

	/**
	 * This overrides the hashcode() method which uses 31 as a base
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mcc == null) ? 0 : mcc.hashCode());
		result = prime * result + ((mnc == null) ? 0 : mnc.hashCode());
		return result;
	}

	/**
	 * This overrides the equals method comparing mcc and mnc
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
		OperatorCountryPK other = (OperatorCountryPK) obj;
		if (mcc == null) {
			if (other.mcc != null)
				return false;
		} else if (!mcc.equals(other.mcc))
			return false;
		if (mnc == null) {
			if (other.mnc != null)
				return false;
		} else if (!mnc.equals(other.mnc))
			return false;
		return true;
	}

	/**
	 * Gets the mcc as an Integer
	 * 
	 * @return mcc
	 */
	public Integer getMcc() {
		return mcc;
	}

	/**
	 * Sets the mcc as an Integer
	 * 
	 * @param mcc
	 */
	public void setMcc(Integer mcc) {
		this.mcc = mcc;
	}

	/**
	 * Gets the mnc as an Integer
	 * 
	 * @return mnc
	 */
	public Integer getMnc() {
		return mnc;
	}

	/**
	 * Sets the mnc as an Integer
	 * 
	 * @param mnc
	 */
	public void setMnc(Integer mnc) {
		this.mnc = mnc;
	}

}
