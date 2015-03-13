package com.team6.project.services.rest;

import java.math.BigInteger;
import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.Record;
import com.team6.project.services.QueryServiceLocal;

/**
 * First attempt at REST query. Basic hard-coded query "working" when URL is entered in browser.
 * Work in progress. 
 * @author James
 *
 */

@Path("/IMSIEvent")
public class CustomerServiceRest {
	
	@Inject
	QueryServiceLocal queryService;
	
	public CustomerServiceRest() {
	}
	
	@GET
	@Path("/{imsi}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<EventCause> findByImsi(@PathParam("imsi") BigInteger imsi){
		
		return  queryService.findByIMSI(imsi);
		
	}
/*
 * 	@GET
	@Path("/{tac}")
	@Produces(MediaType.APPLICATION_JSON)
	public long countCallFailureByTac(@PathParam("tac") Integer tac){
		return queryService.countCallFailureByTac(tac);
	}
 */
	
	

}
