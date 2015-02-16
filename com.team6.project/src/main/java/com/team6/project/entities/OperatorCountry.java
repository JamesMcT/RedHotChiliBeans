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

    public OperatorCountry(Integer mcc, Integer mnc, String country, String operator) {
        super();
        this.mcc = mcc;
        this.mnc = mnc;
        this.country = country;
        this.operator = operator;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mcc == null) ? 0 : mcc.hashCode());
        result = prime * result + ((mnc == null) ? 0 : mnc.hashCode());
        return result;
    }

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
    
    public String toString(){
        return "MCC : "+mcc+" MNC : "+mnc+" Operator : "+operator+" Country : "+country;
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
