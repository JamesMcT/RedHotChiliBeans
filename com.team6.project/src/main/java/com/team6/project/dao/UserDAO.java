package com.team6.project.dao;

import javax.ejb.Local;

import com.team6.project.entities.Response;
import com.team6.project.entities.User;

@Local
public interface UserDAO {
    
    public Response addUser(User user);
    public void updateUser(User user);
    public User getUserByKey(String userId);
    public void daleteUser(User user);

}
