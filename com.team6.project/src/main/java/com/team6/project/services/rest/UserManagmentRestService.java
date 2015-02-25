package com.team6.project.services.rest;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.team6.project.dao.UserDAO;
import com.team6.project.entities.User;
/**
 * 
 * 
 * @author Cristiana
 *
 */
@Path("/usermanagment")
public class UserManagmentRestService {
    
    @EJB
    UserDAO userDao;
    
    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserByKey(@PathParam("userId") String userId){
        return userDao.getUserByKey(userId);
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(User user) {
        userDao.addUser(user);
    }

}
