
package com.team6.project.entities;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Cristiana
 * BaseData table
 */
@Entity
public class BaseData {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private int id;
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
   
   

   public BaseData( Date date, EventCause eventCause, FailureType failure,
		UserEquipment userEquipment, OperatorCountry operatorCountry,
		Integer cellId, Integer duration, String neVersion, BigInteger imsi,
		BigInteger hier3Id, BigInteger hier32Id, BigInteger hier321Id) {
	super();
	this.date = date;
	this.eventCause = eventCause;
	this.failure = failure;
	this.userEquipment = userEquipment;
	this.operatorCountry = operatorCountry;
	this.cellId = cellId;
	this.duration = duration;
	this.neVersion = neVersion;
	this.imsi = imsi;
	this.hier3Id = hier3Id;
	this.hier32Id = hier32Id;
	this.hier321Id = hier321Id;
}



public int getId() {
       return id;
   }

   public void setId(int id) {
       this.id = id;
   }

   public Date getDate() {
       return date;
   }

   public void setDate(Date date) {
       this.date = date;
   }

   public EventCause getEventCause() {
       return eventCause;
   }

   public void setEventCause(EventCause eventCause) {
       this.eventCause = eventCause;
   }

   public FailureType getFailure() {
       return failure;
   }

   public void setFailure(FailureType failure) {
       this.failure = failure;
   }

   public UserEquipment getUserEquipment() {
       return userEquipment;
   }

   public void setUserEquipment(UserEquipment userEquipment) {
       this.userEquipment = userEquipment;
   }

   public OperatorCountry getOperatorCountry() {
       return operatorCountry;
   }

   public void setOperatorCountry(OperatorCountry operatorCountry) {
       this.operatorCountry = operatorCountry;
   }

   public int getCellId() {
       return cellId;
   }

   public void setCellId(int cellId) {
       this.cellId = cellId;
   }

   public int getDuration() {
       return duration;
   }

   public void setDuration(int duration) {
       this.duration = duration;
   }

   public String getNeVersion() {
       return neVersion;
   }

   public void setNeVersion(String neVersion) {
       this.neVersion = neVersion;
   }

   public BigInteger getImsi() {
       return imsi;
   }

   public void setImsi(BigInteger imsi) {
       this.imsi = imsi;
   }

   public BigInteger getHier3Id() {
       return hier3Id;
   }

   public void setHier3Id(BigInteger hier3Id) {
       this.hier3Id = hier3Id;
   }

   public BigInteger getHier32Id() {
       return hier32Id;
   }

   public void setHier32Id(BigInteger hier32Id) {
       this.hier32Id = hier32Id;
   }

   public BigInteger getHier321Id() {
       return hier321Id;
   }

   public void setHier321Id(BigInteger hier321Id) {
       this.hier321Id = hier321Id;
   }


}
