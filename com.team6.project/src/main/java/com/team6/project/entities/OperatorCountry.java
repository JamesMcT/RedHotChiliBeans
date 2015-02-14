package com.team6.project.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
/**
 * @author Cristiana
 * OperatorCountry table
 */
@Entity
@IdClass(OperatorCountryPK.class)
public class OperatorCountry implements Serializable{

    private static final long serialVersionUID = -8219455084678368540L;
    @Id
    private Integer mcc;
    @Id
    private Integer mnc;
    private String country;
    private String operator;
    
    public OperatorCountry() {  }

    public OperatorCountry(int mcc, int mnc, String country, String operator) {
        super();
        this.mcc = mcc;
        this.mnc = mnc;
        this.country = country;
        this.operator = operator;
    }
    
    public boolean hasRequiredFields(){
        if(mcc != null & mnc != null){
            return true;
        }
        return false;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

  
    
}
