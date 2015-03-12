package com.team6.project.dao;

import java.util.Collection;

import javax.ejb.Local;

import com.team6.project.entities.User;

/**
 * @author Cristiana Conti
 */
@Local
public interface UserDAO {
    
	/**
	 * 
	 * @param user
	 */
    public void addUser(User user);
    
    /**
     * 
     * @param user
     */
    public void updateUser(User user);
    
    /**
     * 
     * @param userId
     * @return
     */
    public User getUserByKey(String userId);
    
    /**
     * 
     * @return
     */
    public Collection<User> getAllUser();
    /*public Response deleteUser(User user);*/

}
