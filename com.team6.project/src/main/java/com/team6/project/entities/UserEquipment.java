
package com.team6.project.entities;

import java.io.Serializable;

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
public class UserEquipment implements Serializable{

    private static final long serialVersionUID = 934318010137631519L;

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

    /**
     * 
     * @param tac
     * @param marketingName
     * @param manufacturer
     * @param accessCapability
     * @param model
     * @param vendorName
     * @param type
     * @param os
     * @param inputMode
     */
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

    /**
     * 
     */
   public String toString() {
        return "Tac : " + tac + " Marketing Name : " + marketingName
                + " Manufacturer : " + manufacturer + " Access Capability : "
                + accessCapability + " Model : " + model + " Vendor Name : "
                + vendorName + " Type : " + type + " Operating System : " + os
                + " Input Mode : " + inputMode;
    }

   /**
    * 
    * @return
    */
    public boolean hasRequiredFields() {
        if (tac != null) {
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @return
     */
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

    /**
     * 
     * @return
     */
    public Integer getTac() {
        return tac;
    }

    /**
     * 
     * @param tac
     */
    public void setTac(Integer tac) {
        this.tac = tac;
    }

    /**
     * 
     * @return
     */
    public String getMarketingName() {
        return marketingName;
    }

    /**
     * 
     * @param marketingName
     */
    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }

    /**
     * 
     * @return
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * 
     * @param manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * 
     * @return
     */
    public String getAccessCapability() {
        return accessCapability;
    }

    /**
     * 
     * @param accessCapability
     */
    public void setAccessCapability(String accessCapability) {
        this.accessCapability = accessCapability;
    }

    /**
     * 
     * @return
     */
    public String getModel() {
        return model;
    }

    /**
     * 
     * @param model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * 
     * @return
     */
    public String getVendorName() {
        return vendorName;
    }

    /**
     * 
     * @param vendorName
     */
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    /**
     * 
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     */
    public String getOs() {
        return os;
    }

    /**
     * 
     * @param os
     */
    public void setOs(String os) {
        this.os = os;
    }

    /**
     * 
     * @return
     */
    public String getInputMode() {
        return inputMode;
    }

    /**
     * 
     * @param inputMode
     */
    public void setInputMode(String inputMode) {
        this.inputMode = inputMode;
    }

   

}
