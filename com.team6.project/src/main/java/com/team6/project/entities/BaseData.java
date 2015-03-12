
package com.team6.project.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * BaseData table
 * 
 * @author Cristiana Conti
 * @author Eoin Kernan
 * 
 */
@NamedQueries({
@NamedQuery(name="baseDataCount", query="SELECT COUNT(b.id) FROM BaseData b"),
@NamedQuery(name="getImsiByDate", query="from BaseData b where b.date between :firstDate and :secondDate")
})
@Entity
public class BaseData implements Serializable{
 
   
   //@GeneratedValue(strategy=GenerationType.IDENTITY)
   @Id
   @Column(name="id", unique=true, nullable=false)
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
   @Column(name = "imsi", precision=20, scale=0)
   private BigInteger imsi;
   @Column(name = "hier3Id", precision=20, scale=0)
   private BigInteger hier3Id;
   @Column(name = "hier32Id", precision=20, scale=0)
   private BigInteger hier32Id;
   @Column(name = "hier321Id", precision=20, scale=0)
   private BigInteger hier321Id;
   
   public BaseData() { }
   
   
/**
 * 
 */
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((cellId == null) ? 0 : cellId.hashCode());
	result = prime * result + ((date == null) ? 0 : date.hashCode());
	result = prime * result + ((duration == null) ? 0 : duration.hashCode());
	result = prime * result
			+ ((eventCause == null) ? 0 : eventCause.hashCode());
	result = prime * result + ((failure == null) ? 0 : failure.hashCode());
	result = prime * result + ((hier321Id == null) ? 0 : hier321Id.hashCode());
	result = prime * result + ((hier32Id == null) ? 0 : hier32Id.hashCode());
	result = prime * result + ((hier3Id == null) ? 0 : hier3Id.hashCode());
	result = prime * result + ((imsi == null) ? 0 : imsi.hashCode());
	result = prime * result + ((neVersion == null) ? 0 : neVersion.hashCode());
	result = prime * result
			+ ((operatorCountry == null) ? 0 : operatorCountry.hashCode());
	result = prime * result
			+ ((userEquipment == null) ? 0 : userEquipment.hashCode());
	return result;
}


/**
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
 * 
 * @return
 */
public Integer getKey(){
       return id;
   }


/**
 * 
 * @return
 */
public Integer getId() {
	return id;
}


/**
 * 
 * @param id
 */
public void setId(Integer id) {
	this.id = id;
}


/**
 * 
 * @return
 */
public Date getDate() {
    return date;
}

/**
 * 
 * @param date
 */
public void setDate(Date date) {
    this.date = date;
}

/**
 * 
 * @return
 */
public EventCause getEventCause() {
    return eventCause;
}

/**
 * 
 * @param eventCause
 */
public void setEventCause(EventCause eventCause) {
    this.eventCause = eventCause;
}

/**
 * 
 * @return
 */
public FailureType getFailure() {
    return failure;
}

/**
 * 
 * @param failure
 */
public void setFailure(FailureType failure) {
    this.failure = failure;
}

/**
 * 
 * @return
 */
public UserEquipment getUserEquipment() {
    return userEquipment;
}

/**
 * 
 * @param userEquipment
 */
public void setUserEquipment(UserEquipment userEquipment) {
    this.userEquipment = userEquipment;
}

/**
 * 
 * @return
 */
public OperatorCountry getOperatorCountry() {
    return operatorCountry;
}

/**
 * 
 * @param operatorCountry
 */
public void setOperatorCountry(OperatorCountry operatorCountry) {
    this.operatorCountry = operatorCountry;
}

/**
 * 
 * @return
 */
public Integer getCellId() {
    return cellId;
}

/**
 * 
 * @param cellId
 */
public void setCellId(Integer cellId) {
    this.cellId = cellId;
}

/**
 * 
 * @return
 */
public Integer getDuration() {
    return duration;
}

/**
 * 
 * @param duration
 */
public void setDuration(Integer duration) {
    this.duration = duration;
}

/**
 * 
 * @return
 */
public String getNeVersion() {
    return neVersion;
}

/**
 * 
 * @param neVersion
 */
public void setNeVersion(String neVersion) {
    this.neVersion = neVersion;
}

/**
 * 
 * @return
 */
public BigInteger getImsi() {
    return imsi;
}

/**
 * 
 * @param imsi
 */
public void setImsi(BigInteger imsi) {
    this.imsi = imsi;
}

/**
 * 
 * @return
 */
public BigInteger getHier3Id() {
    return hier3Id;
}

/**
 * 
 * @param hier3Id
 */
public void setHier3Id(BigInteger hier3Id) {
    this.hier3Id = hier3Id;
}

/**
 * 
 * @return
 */
public BigInteger getHier32Id() {
    return hier32Id;
}

/**
 * 
 * @param hier32Id
 */
public void setHier32Id(BigInteger hier32Id) {
    this.hier32Id = hier32Id;
}

/**
 * 
 * @return
 */
public BigInteger getHier321Id() {
    return hier321Id;
}

/**
 * 
 * @param hier321Id
 */
public void setHier321Id(BigInteger hier321Id) {
    this.hier321Id = hier321Id;
}
   
  
}

