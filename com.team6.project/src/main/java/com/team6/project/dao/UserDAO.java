package com.team6.project.dao;

import java.util.Collection;

import javax.ejb.Local;

import com.team6.project.entities.Response;
import com.team6.project.entities.User;

@Local
public interface UserDAO {
    
    public Response addUser(User user);
    public Response updateUser(User user);
    public User getUserByKey(String userId);
    /*public Collection<User> getAllUser();*/
    /*public Response deleteUser(User user);*/

}
