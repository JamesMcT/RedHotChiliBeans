package com.team6.project.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Cristiana UserEquipment table
 */
@Entity
public class UserEquipment {

    @Id
    private Integer tac;
    private String marketingName;
    private String manufacturer;
    private String accessCapability;
    private String model;
    private String vendorName;
    private String type;
    private String os;
    private String inputMode;
    
    public boolean hasRequiredFields(){
        if(tac != null){
            return true;
        }
        return false;
    }

    public Integer getTac() {
        return tac;
    }

    public void setTac(Integer tac) {
        this.tac = tac;
    }

    public String getMarketingName() {
        return marketingName;
    }

    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getAccessCapability() {
        return accessCapability;
    }

    public void setAccessCapability(String accessCapability) {
        this.accessCapability = accessCapability;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getInputMode() {
        return inputMode;
    }

    public void setInputMode(String inputMode) {
        this.inputMode = inputMode;
    }

}
