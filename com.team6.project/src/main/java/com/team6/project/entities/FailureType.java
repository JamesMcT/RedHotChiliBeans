package com.team6.project.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Cristiana FailureType table
 */
@Entity
public class FailureType {

    @Id
    private int failureCode;
    private String description;

    public FailureType() {
    }

    public FailureType(int failureCode, String descrption) {
        super();
        this.failureCode = failureCode;
        this.description = descrption;
    }

    public int getFailureCode() {
        return failureCode;
    }

    public void setFailureCode(int failureCode) {
        this.failureCode = failureCode;
    }

    public String getDescrption() {
        return description;
    }

    public void setDescrption(String descrption) {
        this.description = descrption;
    }

}
