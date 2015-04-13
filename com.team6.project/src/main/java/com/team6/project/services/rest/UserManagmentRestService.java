package com.team6.project.services.rest;

import java.util.Collection;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.team6.project.entities.User;
import com.team6.project.services.PersistenceServiceLocal;
import com.team6.project.services.QueryServiceLocal;

/**
 * Restful Service Class for managing the users of the system
 * 
 * @author Cristiana Conti
 *
 */
@Path("/usermanagement")
public class UserManagmentRestService {

    @Inject
    private PersistenceServiceLocal persistence;

    @Inject
    private QueryServiceLocal query;
    @Context
    private HttpServletResponse response;

    /**
     * Finding the user given by the userId
     * 
     * @param userId
     * @return
     */
    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserByKey(@PathParam("userId") String userId) {
        return query.getUserByKey(userId);
    }

    
    /**
     * Finding all the users
     * 
     * @return
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<User> getAllUser() {
        return query.getAllUser();
    }

    /**
     * Adding new user to the database
     * 
     * @param user
     * @return
     */
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        if (query.getUserByKey(user.getKey()) == null) {
            persistence.addUser(user);
            String result = "User inserted with success";
            return Response.status(Status.OK).entity(result).build();
        }
        String result = "User already into the system";
        return Response.status(Status.BAD_REQUEST).entity(result).build();
    }

    
    /**
     * Update user in the database
     * 
     * @param user
     * @return
     */
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {
        if (query.getUserByKey(user.getKey()) != null) {
            persistence.updateUser(user);
            String result = "User updated with success";
            return Response.status(Status.OK).entity(result).build();
        } 
        String result = "User not found";
        return Response.status(Status.NOT_FOUND).entity(result).build();
       
    }

}
