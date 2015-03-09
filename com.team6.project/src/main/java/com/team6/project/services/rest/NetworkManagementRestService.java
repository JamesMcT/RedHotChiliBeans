package com.team6.project.services.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.team6.project.dao.jpa.JPABaseDataDAO;
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

    @GET
    @Path("/failurecountandduration")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Object[]> getFailureCountAndDurationPerImsiByDate(
    							@QueryParam("startDate") String dateString1,
    							@QueryParam("endDate") String dateString2)
    throws ParseException{
    	
    	SimpleDateFormat sdf = new SimpleDateFormat(JPABaseDataDAO.MYSQL_DATE_FORMAT);
    	
    	return queryService.getFailureCountAndDurationPerImsiByDate(sdf.parse(dateString1), sdf.parse(dateString2));
    }
}
