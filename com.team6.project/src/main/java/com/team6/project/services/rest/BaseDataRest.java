package com.team6.project.services.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;





import com.team6.project.services.QueryServiceLocal;


/**
 * 
 * @author Sabee D14125306
 * This class is dealing with the requests to BaseData table
 */

@Path("/tac")
public class BaseDataRest {
	
	
	@Inject
	QueryServiceLocal queryService;
	
	public BaseDataRest() {}
	
	
	/**
	 * This will count the failures from the BaseData Table for the given tac and time period	 
	 * 
	 */	
	@GET
	@Path("/{tac}")
	@Produces(MediaType.APPLICATION_JSON)
	public long countCallFailureByTac(@PathParam("tac") Integer tac){
		String fromDate_s = "2013-01-11 17:15:00";
		String toDate_s = "2013-01-11 17:16:00";
				
		SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss"); 
		Date fromDate = new Date();
		Date toDate = new Date();
		
		try {
			fromDate = dt.parse(fromDate_s);
			toDate = dt.parse(toDate_s);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
						
		return queryService.countCallFailureByTac(tac, fromDate, toDate);
	}
	
	
}
