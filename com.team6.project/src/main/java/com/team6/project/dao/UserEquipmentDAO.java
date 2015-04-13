package com.team6.project.dao;

import java.util.Collection;

import javax.ejb.Local;

import com.team6.project.entities.UserEquipment;

/**
 * User Equipment Data Access Object
 * <p>
 * User Equipment data access object contains abstract methods to implement CRUD
 * operations for User Equipment object.
 * 
 * @author John O Keeffe
 * @author Eoin Kernan
 * @author James Mc Ternan
 *
 */
@Local
public interface UserEquipmentDAO {

	/**
	 * gets all UserEquipment objects.
	 * 
	 * @return Collection<UserEquipment>
	 */
	public Collection<UserEquipment> getAllUserEquipment();

	/**
	 * gets a UserEquipment Object by a specific tac value. A TAC value is used
	 * to represent a specific handset model. (e.g Iphone 6, Samsung S4 etc.)
	 * 
	 * @param tac
	 * @return UserEquipment
	 */
	public UserEquipment getUserEquipmentByKey(Integer tac);

	/**
	 * Adds a single new unique User Equipment object.
	 * 
	 * @param userEquipment
	 */
	public void addUserEquipment(UserEquipment userEquipment);

	/**
	 * Adds a collection of new userEquipment objects.
	 * 
	 * @param userEquipment
	 */
	public void addUserEquipmentCollection(
			Collection<UserEquipment> userEquipment);

	/**
	 * Allows for non-primary identifiers of UserEquiptment to be update.
	 * (Manufacturers name etc)
	 * 
	 * @param userEquipment
	 */
	public void updateUserEquipment(UserEquipment userEquipment);

	/**
	 * Removes a single UserEquipment object.
	 * 
	 * @param userEquipment
	 */
	public void deleteUserEquipment(UserEquipment userEquipment);

}
