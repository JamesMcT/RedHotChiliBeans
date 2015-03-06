package com.team6.project.services.rest;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.team6.project.entities.EventCause;
import com.team6.project.services.QueryServiceLocal;

/**
 * Restful Service Class for managing the 
 * Network Management Engineer requests
 * 
 * @author Cristiana Conti
 */
@Path("/networkmanagement")
public class NetworkManagementRestService {
    
    @Inject
    QueryServiceLocal queryService;
    
    @GET
    @Path("/eventidcausecode/{userequipment}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Object[]> getDistinctEventByTac(@PathParam("userequipment") String userequipment){
        return queryService.getDistinctEventByTac(userequipment);
    }

}
