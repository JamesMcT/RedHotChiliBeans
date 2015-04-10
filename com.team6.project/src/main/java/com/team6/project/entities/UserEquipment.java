package com.team6.project.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.NamedQuery;

/**
 * This class creates an Entity called FailureType which implements
 * Serializable.
 * 
 * <p>
 * It creates 9 columns: <i>tac</i>, <i>accessCapability</i>,<i>inputMode</i>,
 * <i>manufacturer</i>, <i>marketingName</i>, <i>model</i>,<i>os</i>,
 * <i>type</i> and <i>type</i>.
 * 
 * <p>
 * The is 1 NamedQuery : allUserEquipment
 * 
 * @author Cristiana Conti
 * @author Eoin Kernan
 */
@NamedQuery(name = "allUserEquipment", query = "from UserEquipment")
@Entity
public class UserEquipment implements Serializable {

	private static final long serialVersionUID = 934318010137631519L;

	@Id
	private Integer tac;
	private String marketingName;
	private String manufacturer;
	private String accessCapability;
	private String model;
	private String vendorName;
	private String type;
	private String os;
	private String inputMode;

	public UserEquipment() {
		super();
	}

	/**
	 * 
	 * @param tac
	 * @param marketingName
	 * @param manufacturer
	 * @param accessCapability
	 * @param model
	 * @param vendorName
	 * @param type
	 * @param os
	 * @param inputMode
	 */
	public UserEquipment(Integer tac, String marketingName,
			String manufacturer, String accessCapability, String model,
			String vendorName, String type, String os, String inputMode) {
		super();
		this.tac = tac;
		this.marketingName = marketingName;
		this.manufacturer = manufacturer;
		this.accessCapability = accessCapability;
		this.model = model;
		this.vendorName = vendorName;
		this.type = type;
		this.os = os;
		this.inputMode = inputMode;
	}

	/**
	 * Overrides the toString() method. It overrides to return the tac,
	 * marketingName, manufacturer, accessCapability, accessCapability,
	 * vendorName, type, os and inputMode.
	 * 
	 */
	public String toString() {
		return "Tac : " + tac + " Marketing Name : " + marketingName
				+ " Manufacturer : " + manufacturer + " Access Capability : "
				+ accessCapability + " Model : " + accessCapability
				+ " Vendor Name : " + vendorName + " Type : " + type
				+ " Operating System : " + os + " Input Mode : " + inputMode;
	}

	/**
	 * Checks whether tac is present.
	 * 
	 * @return true if has required field. otherwise false
	 */
	public boolean hasRequiredFields() {
		if (tac != null) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the key as an Integer
	 * 
	 * @return tac
	 */
	public Integer getKey() {
		return tac;
	}

	/**
	 * This overrides the hashcode() method which uses 31 as a base
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tac == null) ? 0 : tac.hashCode());
		return result;
	}

	/**
	 * This overrides the equals method to compare tac
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
		UserEquipment other = (UserEquipment) obj;
		if (tac == null) {
			if (other.tac != null)
				return false;
		} else if (!tac.equals(other.tac))
			return false;
		return true;
	}

	/**
	 * Gets the tac as an Integer
	 * 
	 * @return tac
	 */
	public Integer getTac() {
		return tac;
	}

	/**
	 * Sets the tac as an Integer
	 * 
	 * @param tac
	 */
	public void setTac(Integer tac) {
		this.tac = tac;
	}

	/**
	 * Gets the marketingName as an String
	 * 
	 * @return marketingName
	 */
	public String getMarketingName() {
		return marketingName;
	}

	/**
	 * Sets the marketingName as an String
	 * 
	 * @param marketingName
	 */
	public void setMarketingName(String marketingName) {
		this.marketingName = marketingName;
	}

	/**
	 * Gets the manufacturer as an String
	 * 
	 * @return manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * Sets the manufacturer as an String
	 * 
	 * @param manufacturer
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * Gets the accessCapability as an String
	 * 
	 * @return accessCapability
	 */
	public String getAccessCapability() {
		return accessCapability;
	}

	/**
	 * Sets the accessCapability as an String
	 * 
	 * @param accessCapability
	 */
	public void setAccessCapability(String accessCapability) {
		this.accessCapability = accessCapability;
	}

	/**
	 * Gets the model as an String
	 * 
	 * @return model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Sets the model as an String
	 * 
	 * @param model
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Gets the vendorName as an String
	 * 
	 * @return vendorName
	 */
	public String getVendorName() {
		return vendorName;
	}

	/**
	 * Sets the vendorName as an String
	 * 
	 * @param vendorName
	 */
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	/**
	 * Gets the type as an String
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type as an String
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the os as an String
	 * 
	 * @return os
	 */
	public String getOs() {
		return os;
	}

	/**
	 * Sets the os as an String
	 * 
	 * @param os
	 */
	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * Gets the inputMode as an String
	 * 
	 * @return inputMode
	 */
	public String getInputMode() {
		return inputMode;
	}

	/**
	 * Sets the inputMode as an String
	 * 
	 * @param inputMode
	 */
	public void setInputMode(String inputMode) {
		this.inputMode = inputMode;
	}

}
