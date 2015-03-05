package com.team6.project.services.rest;
import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.team6.project.entities.Response;
import com.team6.project.entities.User;
import com.team6.project.services.PersistenceServiceLocal;
import com.team6.project.services.QueryServiceLocal;
/**
 * 
 * 
 * @author Cristiana
 *
 */
@Path("/usermanagement")
public class UserManagmentRestService {
    
    @Inject
    private PersistenceServiceLocal persistence;
    
    @Inject
    private QueryServiceLocal query;
    
    
    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserByKey(@PathParam("userId") String userId){
        return query.getUserByKey(userId);
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<User> getAllUser(){
        return query.getAllUser();
    }
    
    
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        return persistence.addUser(user);
    }
    
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {
        return persistence.updateUser(user);
    }

}
