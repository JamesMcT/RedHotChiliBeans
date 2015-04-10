package com.team6.project.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

/**
 * Record mapping ErroneousBaseData table
 * 
 * <p>
 * The ErroneousBaseData entity creates id, CellID, a date object, duration,
 * hier321ID, hier21Id, hier3Id, imsi, neVersion, eventId, failureType, mcc,
 * mnc, and UserEquipment.
 * 
 * <p>
 * There is 1 named queries: erroneousRecordCount.
 * 
 * @author Cristiana Conti
 * @author Eoin Kernan
 */
@NamedQuery(name = "erroneousRecordCount", query = "SELECT COUNT(e.id) FROM Record e")
@Entity
@Table(name = "ErroneousBaseData")
public class Record implements IDescription, Serializable {

	private static final long serialVersionUID = 4955975259290289479L;

	protected static org.apache.log4j.Logger recordEntityLogger = org.apache.log4j.Logger
			.getLogger(Record.class);

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	private Date date;
	private Integer causeCode;
	private Integer eventId;
	private Integer failureType;
	private Integer userEquipment;
	private Integer mcc;
	private Integer mnc;
	private Integer cellId;
	private Integer duration;
	private String neVersion;
	@Column(name = "imsi", precision = 20, scale = 0)
	private BigInteger imsi;
	@Column(name = "hier3Id", precision = 20, scale = 0)
	private BigInteger hier3Id;
	@Column(name = "hier32Id", precision = 20, scale = 0)
	private BigInteger hier32Id;
	@Column(name = "hier321Id", precision = 20, scale = 0)
	private BigInteger hier321Id;
	private String description;

	/**
	 * Empty constructor
	 * 
	 */
	public Record() {
	}

	/**
	 * Gets the key as an Integer
	 * 
	 * @return id
	 */
	public Integer getKey() {
		return id;
	}

	/**
	 * Overrides the toString() method. It overrides to return the id, datetime,
	 * causecode, eventId, failureType, userEquipment, mcc, mnc, cellId,
	 * duration, neVersion, imsi, hier3Id, hier32Id, hier321Id and description.
	 * 
	 */
	public String toString() {
		return "Id : " + id + " Date : " + date.getTime() + " Cause Code : "
				+ causeCode + " Event Id : " + eventId + " Failure Type : "
				+ failureType + " User Equipment : " + userEquipment
				+ " MCC : " + mcc + " MNC : " + mnc + " Cell Id : " + cellId
				+ " Duration : " + duration + " NetworkElement Version : "
				+ neVersion + " Imsi : " + imsi + " Hier3Id : " + hier3Id
				+ " Hier32Id : " + hier32Id + " Hier321Id : " + hier321Id
				+ " Description : " + description;

	}

	/**
	 * Overrides the hashcode method using 31 as a base prime.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((causeCode == null) ? 0 : causeCode.hashCode());
		result = prime * result + ((cellId == null) ? 0 : cellId.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
		result = prime * result
				+ ((failureType == null) ? 0 : failureType.hashCode());
		result = prime * result
				+ ((hier321Id == null) ? 0 : hier321Id.hashCode());
		result = prime * result
				+ ((hier32Id == null) ? 0 : hier32Id.hashCode());
		result = prime * result + ((hier3Id == null) ? 0 : hier3Id.hashCode());
		result = prime * result + ((imsi == null) ? 0 : imsi.hashCode());
		result = prime * result + ((mcc == null) ? 0 : mcc.hashCode());
		result = prime * result + ((mnc == null) ? 0 : mnc.hashCode());
		result = prime * result
				+ ((neVersion == null) ? 0 : neVersion.hashCode());
		result = prime * result
				+ ((userEquipment == null) ? 0 : userEquipment.hashCode());
		return result;
	}

	/**
	 * Overrides the equals method which compares each of the ErronsousBaseData
	 * entities instead.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass()) {
			recordEntityLogger.warn("Class");
			return false;
		}
		Record other = (Record) obj;
		if (causeCode == null) {
			if (other.causeCode != null)
				return false;
		} else if (!causeCode.equals(other.causeCode)) {
			return false;
		}
		if (cellId == null) {
			if (other.cellId != null)
				return false;
		} else if (!cellId.equals(other.cellId)) {
			return false;
		}
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (date.getTime() != other.date.getTime()) {
			return false;
		}
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration)) {
			return false;
		}
		if (eventId == null) {
			if (other.eventId != null)
				return false;
		} else if (!eventId.equals(other.eventId)) {
			return false;
		}
		if (failureType == null) {
			if (other.failureType != null)
				return false;
		} else if (!failureType.equals(other.failureType)) {
			return false;
		}
		if (hier321Id == null) {
			if (other.hier321Id != null)
				return false;
		} else if (!hier321Id.equals(other.hier321Id)) {
			return false;
		}
		if (hier32Id == null) {
			if (other.hier32Id != null)
				return false;
		} else if (!hier32Id.equals(other.hier32Id)) {
			return false;
		}
		if (hier3Id == null) {
			if (other.hier3Id != null)
				return false;
		} else if (!hier3Id.equals(other.hier3Id)) {
			return false;
		}
		if (imsi == null) {
			if (other.imsi != null)
				return false;
		} else if (!imsi.equals(other.imsi)) {
			return false;
		}
		if (mcc == null) {
			if (other.mcc != null)
				return false;
		} else if (!mcc.equals(other.mcc)) {
			return false;
		}
		if (mnc == null) {
			if (other.mnc != null)
				return false;
		} else if (!mnc.equals(other.mnc)) {
			return false;
		}
		if (neVersion == null) {
			if (other.neVersion != null)
				return false;
		} else if (!neVersion.equals(other.neVersion)) {
			return false;
		}
		if (userEquipment == null) {
			if (other.userEquipment != null)
				return false;
		} else if (!userEquipment.equals(other.userEquipment)) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the id as an Integer
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id as an Integer
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the date from the BaseData entity as a Date object.
	 * 
	 * @return date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date of the BaseData entity as a Date object.
	 * 
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
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
	 * Sets the causeCode as an Integer
	 * 
	 * @param causeCode
	 */
	public void setCauseCode(Integer causeCode) {
		this.causeCode = causeCode;
	}

	/**
	 * Gets the eventId as an Integer
	 * 
	 * @return eventId
	 */
	public Integer getEventId() {
		return eventId;
	}

	/**
	 * Sets the eventId as an Integer
	 * 
	 * @param eventId
	 */
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	/**
	 * Gets the failureType as an Integer
	 * 
	 * @return failureType
	 */
	public Integer getFailureType() {
		return failureType;
	}

	/**
	 * Sets the failureType as an Integer
	 * 
	 * @param failureType
	 */
	public void setFailureType(Integer failureType) {
		this.failureType = failureType;
	}

	/**
	 * Gets the userEquipment as an Integer
	 * 
	 * @return userEquipment
	 */
	public Integer getUserEquipment() {
		return userEquipment;
	}

	/**
	 * Sets the userEquipment as an Integer
	 * 
	 * @param userEquipment
	 */
	public void setUserEquipment(Integer userEquipment) {
		this.userEquipment = userEquipment;
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
	 * Gets the cellId as an Integer
	 * 
	 * @return cellId
	 */
	public Integer getCellId() {
		return cellId;
	}

	/**
	 * Sets the cellId as an Integer
	 * 
	 * @param cellId
	 */
	public void setCellId(Integer cellId) {
		this.cellId = cellId;
	}

	/**
	 * Gets the duration as an Integer
	 * 
	 * @return duration
	 */
	public Integer getDuration() {
		return duration;
	}

	/**
	 * Sets the duration as an Integer
	 * 
	 * @param duration
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	/**
	 * Gets the neVersion as an Integer
	 * 
	 * @return neVersion
	 */
	public String getNeVersion() {
		return neVersion;
	}

	/**
	 * Sets the neVersion as an Integer
	 * 
	 * @param neVersion
	 */
	public void setNeVersion(String neVersion) {
		this.neVersion = neVersion;
	}

	/**
	 * Gets the imsi as a BigInteger
	 * 
	 * @return imsi
	 */
	public BigInteger getImsi() {
		return imsi;
	}

	/**
	 * Sets the imsi as a BigInteger
	 * 
	 * @param imsi
	 */
	public void setImsi(BigInteger imsi) {
		this.imsi = imsi;
	}

	/**
	 * Gets the hier3Id as a BigInteger
	 * 
	 * @return hier3Id
	 */
	public BigInteger getHier3Id() {
		return hier3Id;
	}

	/**
	 * Sets the hier3Id as a BigInteger
	 * 
	 * @param hier3Id
	 */
	public void setHier3Id(BigInteger hier3Id) {
		this.hier3Id = hier3Id;
	}

	/**
	 * Gets the hier32Id as a BigInteger
	 * 
	 * @return hier32Id
	 */
	public BigInteger getHier32Id() {
		return hier32Id;
	}

	/**
	 * Sets the hier32Id as a BigInteger
	 * 
	 * @param hier32Id
	 */
	public void setHier32Id(BigInteger hier32Id) {
		this.hier32Id = hier32Id;
	}

	/**
	 * Gets the hier321Id as a BigInteger
	 * 
	 * @return hier321Id
	 */
	public BigInteger getHier321Id() {
		return hier321Id;
	}

	/**
	 * Sets the hier321Id as a BigInteger
	 * 
	 * @param hier321Id
	 */
	public void setHier321Id(BigInteger hier321Id) {
		this.hier321Id = hier321Id;
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
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
