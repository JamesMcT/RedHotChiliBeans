package com.team6.project.entities;

public class Response {
    public enum Status {
        OK, ERROR, NOT_FOUND
        
    }
    private String description;
    private Status status;
    
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    
      
    
    
}
