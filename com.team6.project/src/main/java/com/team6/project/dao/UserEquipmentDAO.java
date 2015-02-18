package com.team6.project.dao;

import java.util.Collection;

import javax.ejb.Local;

import com.team6.project.entities.UserEquipment;

@Local
public interface UserEquipmentDAO {

	public Collection<UserEquipment> getAllUserEquipment();
	
	public UserEquipment getUserEquipmentByKey(Integer tac);
	
	public void addUserEquipment(UserEquipment userEquipment);

	public void updateUserEquipment(UserEquipment userEquipment);

	public void deleteUserEquipment(UserEquipment userEquipment);

//	public void deleteByTac(Integer tac);
	
//	public UserEquipment findByTac(Integer tac);

//	public void deleteAll();
	
	
}
