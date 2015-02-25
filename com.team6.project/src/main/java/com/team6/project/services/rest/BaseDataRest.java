package com.team6.project.services.rest;

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
 *
 */

@Path("/tac")
public class BaseDataRest {
	
	
	@Inject
	QueryServiceLocal queryService;
	
	public BaseDataRest() {}
	
	@GET
	@Path("/{tac}")
	@Produces(MediaType.APPLICATION_JSON)
	public long countCallFailureByTac(@PathParam("tac") Integer tac){
		return queryService.countCallFailureByTac(tac);
	}
	
	
}
