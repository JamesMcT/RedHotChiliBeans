package com.team6.project.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Cristiana FailureType table
 */
@Entity
public class FailureType {

    @Id
    private Integer failureCode;
    private String description;

    public FailureType() {
    }

    public FailureType(Integer failureCode, String descrption) {
        super();
        this.failureCode = failureCode;
        this.description = descrption;
    }
    
    public boolean hasRequiredFields(){
        if(failureCode != null){
            return true;
        }
        return false;
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
