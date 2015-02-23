
package com.team6.project.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.NamedQuery;

/**
 * UserEquipment table
 * 
 * @author Cristiana Conti
 * @author Eoin Kernan
 */

@NamedQuery(name="allUserEquipment", query="from UserEquipment")
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

    public UserEquipment() {
        super();
    }

    public UserEquipment(Integer tac, String marketingName,
            String manufacturer, String accessCapability, String model,
            String vendorName, String type, String os, String inputMode) {
        super();
        this.tac = tac;
        this.marketingName = marketingName;
        this.manufacturer = manufacturer;
        this.accessCapability = accessCapability;
        this.model = model;
        this.vendorName = vendorName;
        this.type = type;
        this.os = os;
        this.inputMode = inputMode;
    }

   public String toString() {
        return "Tac : " + tac + " Marketing Name : " + marketingName
                + " Manufacturer : " + manufacturer + " Access Capability : "
                + accessCapability + " Model : " + model + " Vendor Name : "
                + vendorName + " Type : " + type + " Operating System : " + os
                + " Input Mode : " + inputMode;
    }

    public boolean hasRequiredFields() {
        if (tac != null) {
            return true;
        }
        return false;
    }
    
    public Integer getKey(){
        return tac;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tac == null) ? 0 : tac.hashCode());
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
        UserEquipment other = (UserEquipment) obj;
        if (tac == null) {
            if (other.tac != null)
                return false;
        } else if (!tac.equals(other.tac))
            return false;
        return true;
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
