package com.team6.project.services.rest;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.Record;
import com.team6.project.services.QueryServiceLocal;

/**
 * First attempt at REST query. Basic hard-coded query "working" when URL is entered in browser.
 * Work in progress. 
 * @author James
 *
 */

@Path("/IMSIEvent")
public class IMSIEventRest {
	
	@Inject
	QueryServiceLocal queryService;
	
	public IMSIEventRest() {
	}
	
	@GET
	@Path("/IMSI")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<BaseData> findByImsi(){
		
		return queryService.findByIMSI();
		
	}

	
	

}
