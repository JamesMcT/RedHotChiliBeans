package com.team6.project.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * This class creates an Entity called OperatorCountry which implements
 * Serializable and uses the composite key called OperatorCountryPK.
 * 
 * <p>
 * It creates 3 columns called <i> mcc </i>,<i> mnc </i>, <i> country </i> and
 * <i> operator </i>
 * 
 * @author Cristiana
 */
@Entity
@IdClass(OperatorCountryPK.class)
public class OperatorCountry implements Serializable {

	private static final long serialVersionUID = -8219455084678368540L;
	@Id
	private Integer mcc;
	@Id
	private Integer mnc;
	private String country;
	private String operator;

	/**
	 * Empty constructor for OperatorCountry
	 * 
	 */
	public OperatorCountry() {
	}

	/**
	 * Constructor for OperatorCountry
	 * 
	 * @param mcc
	 * @param mnc
	 * @param country
	 * @param operator
	 */
	public OperatorCountry(Integer mcc, Integer mnc, String country,
			String operator) {
		super();
		this.mcc = mcc;
		this.mnc = mnc;
		this.country = country;
		this.operator = operator;
	}

	/**
	 * Overrides the toString() method. It overrides to return the mcc, mnc,
	 * operator and country
	 * 
	 */
	public String toString() {
		return "MCC : " + mcc + " MNC : " + mnc + " Operator : " + operator
				+ " Country : " + country;
	}

	/**
	 * Checks whether mcc and mnc is present.
	 * 
	 * @return true if has required fields. otherwise false
	 */
	public boolean hasRequiredFields() {
		if (mcc != null & mnc != null) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the composite key of the OperatorCountryPK
	 * 
	 * @return OperatorCountryPK object with mcc and mnc
	 */
	public OperatorCountryPK getKey() {
		return new OperatorCountryPK(mcc, mnc);
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
		OperatorCountry other = (OperatorCountry) obj;
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

	/**
	 * Gets the country as a String
	 * 
	 * @return country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country as a String
	 * 
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the operator as a String
	 * 
	 * @return operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * Sets the operator as a String
	 * 
	 * @param operator
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

}
