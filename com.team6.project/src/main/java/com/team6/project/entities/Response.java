package com.team6.project.entities;

/**
 * 
 * @author Cristiana Conti
 *
 */
public class Response {
   
	public enum Status {
        OK, ERROR, NOT_FOUND
        
    }
    private String description;
    private Status status;
    
    /**
     * 
     * @return
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * 
     * @return
     */
    public Status getStatus() {
        return status;
    }
    
    /**
     * 
     * @param status
     */
    public void setStatus(Status status) {
        this.status = status;
    }
       
    
}
