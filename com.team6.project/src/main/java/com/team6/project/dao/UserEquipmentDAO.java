package com.team6.project.dao;

import java.util.Collection;

import javax.ejb.Local;

import com.team6.project.entities.UserEquipment;

/**
 * 
 * @author John O Keeffe
 * @author Eoin Kernan
 *
 */
@Local
public interface UserEquipmentDAO {

	/**
	 * 
	 * @return
	 */
	public Collection<UserEquipment> getAllUserEquipment();
	
	/**
	 * 
	 * @param tac
	 * @return
	 */
	public UserEquipment getUserEquipmentByKey(Integer tac);
	
	/**
	 * 
	 * @param userEquipment
	 */
	public void addUserEquipment(UserEquipment userEquipment);
	
	/**
	 * 
	 * @param userEquipment
	 */
	public void addUserEquipmentCollection(Collection<UserEquipment> userEquipment);

	/**
	 * 
	 * @param userEquipment
	 */
	public void updateUserEquipment(UserEquipment userEquipment);

	/**
	 * 
	 * @param userEquipment
	 */
	public void deleteUserEquipment(UserEquipment userEquipment);

//	public void deleteByTac(Integer tac);
	
//	public UserEquipment findByTac(Integer tac);

//	public void deleteAll();
	
	
}
