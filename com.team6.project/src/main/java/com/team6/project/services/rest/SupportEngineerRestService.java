package com.team6.project.services.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.team6.project.dao.jpa.JPABaseDataDAO;
import com.team6.project.entities.BaseData;
import com.team6.project.entities.FailureType;
import com.team6.project.services.QueryServiceLocal;

/**
 * 
 * @author Sabee D14125306
 *
 */

@Path("/supportengineer")
public class SupportEngineerRestService {

	@Inject
	QueryServiceLocal queryService;

	public SupportEngineerRestService() {
	}

	@GET
	@Path("/tac")
	@Produces(MediaType.APPLICATION_JSON)
	public long countCallFailureByTac(@QueryParam("tac") Integer tac,
			@QueryParam("fromDate") long lFromDate,
			@QueryParam("toDate") long lToDate) {

		Date fromDate = new Date();
		Date toDate = new Date();

		fromDate.setTime(lFromDate);
		toDate.setTime(lToDate);

		return queryService.countCallFailureByTac(tac, fromDate, toDate);
	}	
		

	@GET
	@Path("/datequery")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<BaseData> findImsiByDate(
			@QueryParam("firstDate") long lfirstDate,
			@QueryParam("secondDate") long lsecondDate) {
	    
	    System.err.println("the first param is.."+lfirstDate);
	    Date fromDate = new Date();
        Date toDate = new Date();

        fromDate.setTime(lfirstDate);
        toDate.setTime(lsecondDate);
        System.err.println("the first data is.."+fromDate.toString());
        System.err.println("the second data is.."+toDate.toString());
		return queryService.findImsiByDate(fromDate, toDate);
	}
	
	
	@GET
	@Path("/failurecode")
	@Produces(MediaType.APPLICATION_JSON)
	  public Collection<Object[]>  getImsiByFailureCode(@QueryParam("failureCode") Integer failureCode) {

		

		return queryService.getImsiByFailureCode(failureCode);
	}
	  
	  @GET
		@Path("/failuretype")
		@Produces(MediaType.APPLICATION_JSON)
		  public Collection<FailureType>  getAllFailureTypes() {

			

			return queryService.getAllFailureTypes();
		} 

}
