package com.team6.project.dao;

import java.util.Collection;

import javax.ejb.Local;

import com.team6.project.entities.User;

/**
 * User Data Access Object
 * <p>
 * The User Data access object contains abstract methods to implement CRUD
 * operations.
 * 
 * @author Cristiana Conti
 * @author James Mc Ternan
 */
@Local
public interface UserDAO {

	/**
	 * Add new user.
	 * 
	 * @param user
	 */
	public void addUser(User user);

	/**
	 * Update existing user.
	 * 
	 * @param user
	 */
	public void updateUser(User user);

	/**
	 * Get user by Id.
	 * 
	 * @param userId
	 * @return User
	 */
	public User getUserByKey(String userId);

	/**
	 * Get all Users.
	 * 
	 * @return Collection<User>
	 */
	public Collection<User> getAllUser();

}
