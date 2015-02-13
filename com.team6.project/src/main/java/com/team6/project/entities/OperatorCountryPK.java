package com.team6.project.entities;

import java.io.Serializable;

/**
 * @author Cristiana Additional class for implementing composite key
 */
public class OperatorCountryPK implements Serializable {

    private static final long serialVersionUID = -8462976575139159548L;
    private int mcc;
    private int mnc;

    public OperatorCountryPK() {
        // TODO Auto-generated constructor stub
    }

    public OperatorCountryPK(int mcc, int mnc) {
        super();
        this.mcc = mcc;
        this.mnc = mnc;
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
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + mcc;
        result = prime * result + mnc;
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
        if (mcc != other.mcc)
            return false;
        if (mnc != other.mnc)
            return false;
        return true;
    }

}
