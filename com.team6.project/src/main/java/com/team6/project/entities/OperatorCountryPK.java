
package com.team6.project.entities;

import java.io.Serializable;

/**
 * @author Cristiana Additional class for implementing composite key
 */
public class OperatorCountryPK implements Serializable {

    private static final long serialVersionUID = -8462976575139159548L;
    private Integer mcc;
    private Integer mnc;

    /**
     * 
     */
    public OperatorCountryPK() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * 
     */
    public String toString() {
        return "MCC : " + mcc + " MNC : " + mnc;
    }

    /**
     * 
     * @param mcc
     * @param mnc
     */
    public OperatorCountryPK(Integer mcc, Integer mnc) {
        super();
        this.mcc = mcc;
        this.mnc = mnc;
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
        OperatorCountryPK other = (OperatorCountryPK) obj;
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
     * 
     * @return
     */
    public Integer getMcc() {
        return mcc;
    }

    /**
     * 
     * @param mcc
     */
    public void setMcc(Integer mcc) {
        this.mcc = mcc;
    }

    /**
     * 
     * @return
     */
    public Integer getMnc() {
        return mnc;
    }

    /**
     * 
     * @param mnc
     */
    public void setMnc(Integer mnc) {
        this.mnc = mnc;
    }

}
