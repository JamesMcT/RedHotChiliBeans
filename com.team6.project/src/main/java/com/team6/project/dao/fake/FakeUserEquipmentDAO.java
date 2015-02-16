package com.team6.project.dao.fake;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;

import com.team6.project.dao.UserEquipmentDAO;
import com.team6.project.entities.UserEquipment;

/**
 * @author Sabee
 * 
 */

@Stateless
@Local
public class FakeUserEquipmentDAO implements UserEquipmentDAO{

	private static Map<Integer, UserEquipment> uEquipments = new HashMap<Integer, UserEquipment>();
//    static {
//    	uEquipments.put(0, new UserEquipment(00000000,"MN00","SonyEricsson", "GSM 900", "M00", "SonyEricsson", "HANDHELD", "OS00", "BASIC"));
//        uEquipments.put(1, new UserEquipment(11111111,"MN11", "Nokia", "GSM 1800", "M11", "Nokia", "HANDHELD", null, null));
//        uEquipments.put(2, new UserEquipment(22222222,"MN22", "Siemens", "GSM 1900", "M22", "Siemens", null, null, null));       
//    }

    
    public FakeUserEquipmentDAO() {
    	UserEquipment ue;
    	
    	ue = new UserEquipment();    	
    	ue.setTac(00000000);
    	ue.setMarketingName("MN00");
    	ue.setManufacturer("SonyEricsson");
    	ue.setAccessCapability("GSM 900");
    	ue.setModel("M00");
    	ue.setVendorName("SonyEricsson");
    	ue.setType("HANDHELD");
    	ue.setOs("OS00");
    	ue.setInputMode("BASIC");    	
    	uEquipments.put(0, ue);
    	
    	ue = new UserEquipment();    	
    	ue.setTac(11111111);
    	ue.setMarketingName("MN11");
    	ue.setManufacturer("Nokia");
    	ue.setAccessCapability("GSM 1800");
    	ue.setModel("M11");
    	ue.setVendorName("Nokia");
    	ue.setType(null);
    	ue.setOs(null);
    	ue.setInputMode(null);
    	uEquipments.put(1, ue);
    	
    	ue = new UserEquipment();    	
    	ue.setTac(22222222);
    	ue.setMarketingName("MN22");
    	ue.setManufacturer("Siemens");
    	ue.setAccessCapability("GSM 1900");
    	ue.setModel("M22");
    	ue.setVendorName("Siemens");
    	ue.setType(null);
    	ue.setOs(null);
    	ue.setInputMode(null);
    	uEquipments.put(2, ue);
    	
	}
    
    
	@Override
	public Collection<UserEquipment> getRecords() {
		return uEquipments.values();
	}
	@Override
	public void addNewUserEquipmentDataSet(UserEquipment userEquipment) {
		uEquipments.put(userEquipment.getTac(), userEquipment);
		
	}
	@Override
	public void updateUserEquipment(UserEquipment userEquipment) {
		uEquipments.put(userEquipment.getTac(), userEquipment);		
	}
	
	@Override
	public UserEquipment findByTac(Integer tac) {
		return uEquipments.get(tac);		
	}
	
	@Override
	public void deleteByTac(Integer tac) {
		uEquipments.remove(tac);
		
	}
	@Override
	public void deleteAll() {
		uEquipments.clear();		
	}

    
}
