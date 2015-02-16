package com.team6.project.entities;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Cristiana Record mapping ErroneousBaseData table
 */
@Entity
@Table(name = "ErroneousBaseData")
public class Record implements IDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
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

    public Record() {
    }

    
    
    
   public boolean asserSameFields(Record other) {
        if (causeCode == null) {
            if (other.causeCode != null)
                return false;
        } else if (!causeCode.equals(other.causeCode))
            return false;
        if (cellId == null) {
            if (other.cellId != null)
                return false;
        } else if (!cellId.equals(other.cellId))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (duration == null) {
            if (other.duration != null)
                return false;
        } else if (!duration.equals(other.duration))
            return false;
        if (eventId == null) {
            if (other.eventId != null)
                return false;
        } else if (!eventId.equals(other.eventId))
            return false;
        if (failureType == null) {
            if (other.failureType != null)
                return false;
        } else if (!failureType.equals(other.failureType))
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
        if (neVersion == null) {
            if (other.neVersion != null)
                return false;
        } else if (!neVersion.equals(other.neVersion))
            return false;
        if (userEquipment == null) {
            if (other.userEquipment != null)
                return false;
        } else if (!userEquipment.equals(other.userEquipment))
            return false;
        return true;
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

    public Integer getCauseCode() {
        return causeCode;
    }

    public void setCauseCode(Integer causeCode) {
        this.causeCode = causeCode;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getFailureType() {
        return failureType;
    }

    public void setFailureType(Integer failureType) {
        this.failureType = failureType;
    }

    public Integer getUserEquipment() {
        return userEquipment;
    }

    public void setUserEquipment(Integer userEquipment) {
        this.userEquipment = userEquipment;
    }

    public Integer getMcc() {
        return mcc;
    }

    public void setMcc(Integer mcc) {
        this.mcc = mcc;
    }

    public Integer getMnc() {
        return mnc;
    }

    public void setMnc(Integer mnc) {
        this.mnc = mnc;
    }

    public Integer getCellId() {
        return cellId;
    }

    public void setCellId(Integer cellId) {
        this.cellId = cellId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   


}