package com.team6.project.dao;

import java.util.Collection;

import com.team6.project.entities.UserEquipment;


public interface UserEquipmentDAO {

	public Collection<UserEquipment> getRecords();
	
	public void addNewUserEquipmentDataSet(UserEquipment userEquipment);

	public void updateUserEquipment(UserEquipment userEquipment);

	public UserEquipment findByTac(Integer tac);

	public void deleteByTac(Integer tac);

	public void deleteAll();
	
}