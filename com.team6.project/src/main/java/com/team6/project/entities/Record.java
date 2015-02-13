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

    public int getCauseCode() {
        return causeCode;
    }

    public void setCauseCode(int causeCode) {
        this.causeCode = causeCode;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getFailureType() {
        return failureType;
    }

    public void setFailureType(int failureType) {
        this.failureType = failureType;
    }

    public int getUserEquipment() {
        return userEquipment;
    }

    public void setUserEquipment(int userEquipment) {
        this.userEquipment = userEquipment;
    }

    public int getMcc() {
        return mcc;
    }

    public void setMcc(int mcc) {
        this.mcc = mcc;
    }

    public int getMnc() {
        return mnc;
    }

    public void setMnc(int mnc) {
        this.mnc = mnc;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}