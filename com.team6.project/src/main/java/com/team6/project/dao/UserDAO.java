package com.team6.project.dao;

import javax.ejb.Local;

import com.team6.project.entities.Response;
import com.team6.project.entities.User;

/**
 * 
 * @author John O Keeffe
 *
 */
@Local
public interface UserDAO {
    
	/**
	 * 
	 * @param user
	 * @return
	 */
    public Response addUser(User user);
    
    /**
     * 
     * @param user
     * @return
     */
    public Response updateUser(User user);
    
    /**
     * 
     * @param userId
     * @return
     */
    public User getUserByKey(String userId);
    /*public Response deleteUser(User user);*/

}
