package com.team6.project.services.rest;
import java.util.Collection;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.team6.project.dao.UserDAO;
import com.team6.project.entities.Response;
import com.team6.project.entities.User;
/**
 * 
 * 
 * @author Cristiana
 *
 */
@Path("/usermanagement")
public class UserManagmentRestService {
    
    @EJB
    UserDAO userDao;
    
    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserByKey(@PathParam("userId") String userId){
        return userDao.getUserByKey(userId);
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<User> getAllUser(){
        return userDao.getAllUser();
    }
    
    
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        return userDao.addUser(user);
    }
    
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {
        return userDao.updateUser(user);
    }

}
