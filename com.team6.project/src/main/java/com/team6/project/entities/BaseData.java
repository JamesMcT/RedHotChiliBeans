package com.team6.project.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * This class is for the entity called BaseData and implements Serializable.
 *
 * <p>
 * There are many to one relationshisp with the
 * <i>EventCase</i>,<i>FailureType</i>,<i>OperatorCountry</i> and
 * <i>UserEquipment</i> tables.
 *
 * <p>
 * The BaseData entity creates id, CellID, a date object, duration, hier321ID,
 * hier21Id, hier3Id, imsi, neVersion and EventCause object, FailureCode object,
 * OperatorCountry object and UserEquipment object.
 * 
 * <p>
 * There are 6 named queries: BaseData.findEventCauseByImsi, baseDataCount,
 * getImsiByDate, eventCauseAndIdByTac, getAllImsi and
 * failureCountAndDurationPerImsiByDate
 * 
 * 
 * @author Cristiana BaseData table
 * @author James NamedQueries BaseData table
 * @author Sabee
 * @author John O Keeffe
 */
@NamedQueries({
		@NamedQuery(name = "BaseData.findEventCauseByImsi", query = "SELECT b.eventCause FROM BaseData b WHERE b.imsi = :imsi"),
		@NamedQuery(name = "imsiByFailureCode", query = "SELECT b.imsi, b.date, b.failure FROM BaseData b WHERE b.failure.failureCode = :failureCode"),

		@NamedQuery(name = "baseDataCount", query = "SELECT COUNT(b.id) FROM BaseData b"),
		//@NamedQuery(name = "getImsiByDate", query = "SELECT distinct(b.imsi) from BaseData b where b.date between :firstDate and :secondDate"),
		@NamedQuery(name = "getImsiByDate", query = "SELECT distinct(b) from BaseData b where b.date between :firstDate and :secondDate  group by b.date, b.imsi ORDER BY b.date DESC"),
		@NamedQuery(name = "eventCauseAndIdByTac", query = "SELECT b.eventCause, COUNT(b) FROM BaseData b where b.userEquipment.tac=:userEquipment GROUP BY b.eventCause"),
		@NamedQuery(name = "getAllImsi", query = "SELECT distinct(b.imsi) FROM BaseData b"),	// Query needed for performance tests.		
		@NamedQuery(name = "countCallFailureByTac", query = "select count(b) from BaseData b where b.userEquipment.tac = :tac and b.date >= :fromDate and b.date <= :toDate"),
		@NamedQuery(name = "getTOP10MarketOperatorCellByDate", query = "select b, count(b) from BaseData b where b.date >= :fromDate and b.date <= :toDate group by b.operatorCountry.mcc, b.operatorCountry.mnc, b.cellId order by count(b) desc" ),				
		@NamedQuery(name = "getUniqueEventCauseByImsi", query ="SELECT b.eventCause, COUNT(b) FROM BaseData b where b.imsi = :imsi GROUP BY b.eventCause"),

		@NamedQuery(name = "failureCountAndDurationPerImsiByDate", query = "SELECT b.imsi, COUNT(b.id), SUM(b.duration) FROM BaseData b WHERE b.date >=:startDate AND b.date <=:endDate GROUP BY b.imsi ORDER BY count(b.id) DESC"),
		@NamedQuery(name = "topTenFailuresByDate", query = "SELECT b.imsi, COUNT(b.id) FROM BaseData b WHERE b.date >=:startDate AND b.date <=:endDate GROUP BY b.imsi ORDER BY count(b.id) DESC")

})
@Entity
public class BaseData implements Serializable {

	/**
	 * Gets the id of the BaseData table as an Integer
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id of the BaseData table as an Integer
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	private Date date;
	@ManyToOne
	private EventCause eventCause;
	@ManyToOne
	private FailureType failure;
	@ManyToOne
	private UserEquipment userEquipment;
	@ManyToOne
	private OperatorCountry operatorCountry;
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

	/**
	 * Empty BaseData constructor
	 */
	public BaseData() {
	}

	/**
	 * Overrides the hashcode method using 31 as a base prime.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cellId == null) ? 0 : cellId.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((duration == null) ? 0 : duration.hashCode());
		result = prime * result
				+ ((eventCause == null) ? 0 : eventCause.hashCode());
		result = prime * result + ((failure == null) ? 0 : failure.hashCode());
		result = prime * result
				+ ((hier321Id == null) ? 0 : hier321Id.hashCode());
		result = prime * result
				+ ((hier32Id == null) ? 0 : hier32Id.hashCode());
		result = prime * result + ((hier3Id == null) ? 0 : hier3Id.hashCode());
		result = prime * result + ((imsi == null) ? 0 : imsi.hashCode());
		result = prime * result
				+ ((neVersion == null) ? 0 : neVersion.hashCode());
		result = prime * result
				+ ((operatorCountry == null) ? 0 : operatorCountry.hashCode());
		result = prime * result
				+ ((userEquipment == null) ? 0 : userEquipment.hashCode());
		return result;
	}

	/**
	 * Overrides the equals method which compares each of the BaseData entities
	 * instead.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseData other = (BaseData) obj;
		if (cellId == null) {
			if (other.cellId != null)
				return false;
		} else if (!cellId.equals(other.cellId))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (date.getTime() != other.date.getTime())
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (eventCause == null) {
			if (other.eventCause != null)
				return false;
		} else if (!eventCause.equals(other.eventCause))
			return false;
		if (failure == null) {
			if (other.failure != null)
				return false;
		} else if (!failure.equals(other.failure))
			return false;
		if (hier321Id == null) {
			if (other.hier321Id != null)
				return false;
		} else if (!hier321Id.equals(other.hier321Id))
			return false;
		if (hier32Id == null) {
			if (other.hier32Id != null)
				return false;
		} else if (!hier32Id.equals(other.hier32Id))
			return false;
		if (hier3Id == null) {
			if (other.hier3Id != null)
				return false;
		} else if (!hier3Id.equals(other.hier3Id))
			return false;
		if (imsi == null) {
			if (other.imsi != null)
				return false;
		} else if (!imsi.equals(other.imsi))
			return false;
		if (neVersion == null) {
			if (other.neVersion != null)
				return false;
		} else if (!neVersion.equals(other.neVersion))
			return false;
		if (operatorCountry == null) {
			if (other.operatorCountry != null)
				return false;
		} else if (!operatorCountry.equals(other.operatorCountry))
			return false;
		if (userEquipment == null) {
			if (other.userEquipment != null)
				return false;
		} else if (!userEquipment.equals(other.userEquipment))
			return false;
		return true;
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
	 * Gets the EventCause Object
	 * 
	 * @return eventCause
	 */
	public EventCause getEventCause() {
		return eventCause;
	}

	/**
	 * Sets the EventCause Object
	 * 
	 * @param eventCause
	 */
	public void setEventCause(EventCause eventCause) {
		this.eventCause = eventCause;
	}

	/**
	 * Gets the FailureType object
	 * 
	 * @return failure
	 */
	public FailureType getFailure() {
		return failure;
	}

	/**
	 * Sets the FailureType object
	 * 
	 * @param failure
	 */
	public void setFailure(FailureType failure) {
		this.failure = failure;
	}

	/**
	 * Gets the UserEquipment object
	 * 
	 * @return userEquipment
	 */
	public UserEquipment getUserEquipment() {
		return userEquipment;
	}

	/**
	 * Sets the UserEquipment object
	 * 
	 * @param userEquipment
	 */
	public void setUserEquipment(UserEquipment userEquipment) {
		this.userEquipment = userEquipment;
	}

	/**
	 * Gets the OperatorCountry object
	 * 
	 * @return operatorCountry
	 */
	public OperatorCountry getOperatorCountry() {
		return operatorCountry;
	}

	/**
	 * Sets the OperatorCountry object
	 * 
	 * @param operatorCountry
	 */
	public void setOperatorCountry(OperatorCountry operatorCountry) {
		this.operatorCountry = operatorCountry;
	}

	/**
	 * Gets the CellId as an Integer
	 * 
	 * @return cellId
	 */
	public Integer getCellId() {
		return cellId;
	}

	/**
	 * Sets the CellId as an Integer
	 * 
	 * @param cellId
	 */
	public void setCellId(Integer cellId) {
		this.cellId = cellId;
	}

	/**
	 * Gets the call Duration as an Integer
	 * 
	 * @return duration
	 */
	public Integer getDuration() {
		return duration;
	}

	/**
	 * Sets the call Duration as an Integer
	 * 
	 * @param duration
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	/**
	 * Gets the neVersion as a String
	 * 
	 * @return neVersion
	 */
	public String getNeVersion() {
		return neVersion;
	}

	/**
	 * Sets the neVersion as a String
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
	 * Gets the hier3id as a BigInteger
	 * 
	 * @return hier3Id
	 */
	public BigInteger getHier3Id() {
		return hier3Id;
	}

	/**
	 * Sets the hier3id as a BigInteger
	 * 
	 * @param hier3Id
	 */
	public void setHier3Id(BigInteger hier3Id) {
		this.hier3Id = hier3Id;
	}

	/**
	 * Gets the hier32id as a BigInteger
	 * 
	 * @return hier32Id
	 */
	public BigInteger getHier32Id() {
		return hier32Id;
	}

	/**
	 * Sets the hier32id as a BigInteger
	 * 
	 * @param hier32Id
	 */
	public void setHier32Id(BigInteger hier32Id) {
		this.hier32Id = hier32Id;
	}

	/**
	 * Gets the hier321id as a BigInteger
	 * 
	 * @return hier321Id
	 */
	public BigInteger getHier321Id() {
		return hier321Id;
	}

	/**
	 * Sets the hier321id as a BigInteger
	 * 
	 * @param hier321Id
	 */
	public void setHier321Id(BigInteger hier321Id) {
		this.hier321Id = hier321Id;
	}

}
