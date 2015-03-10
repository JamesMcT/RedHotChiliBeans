package com.team6.project.services.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.stream.FileImageOutputStream;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.soap.AddressingFeature.Responses;

import com.team6.project.entities.ReqParam;
import com.team6.project.entities.Response;
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
	@Produces(MediaType.APPLICATION_JSON)
	public long countCallFailureByTac(
			@QueryParam("tac") Integer tac,
			@QueryParam("fromDate") long lFromDate,
			@QueryParam("toDate") long lToDate){
	
	
		Date fromDate = new Date();		
		Date toDate = new Date();
		
		fromDate.setTime(lFromDate);
		toDate.setTime(lToDate);
		
		return queryService.countCallFailureByTac(tac, fromDate, toDate);
	}
	
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response countCallFailureByTac(ReqParam reqParam) {        
        
//        String fromDate_s = "2013-01-11 17:15:00";
//		String toDate_s = "2013-01-11 17:16:00";
				
		SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss"); 
		Date fromDate = new Date();
		Date toDate = new Date();
		
		try {
			fromDate = dt.parse(reqParam.getFromDate());
			toDate = dt.parse(reqParam.getToDate());
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		
		Integer tac = new Integer(reqParam.getTac());		
		
		
		//FileIOtxt f = new FileIOtxt("sabee20150307.tmp");
		//f.saveFile(queryService.countCallFailureByTac(tac, fromDate, toDate).getDescription());
		
//		Response response = new Response();
//		
//		response.setStatus(Response.Status.OK);
//		response.setDescription("666666");
		
		
		return queryService.countCallFailureByTacPOST(tac, fromDate, toDate);
        
	}
	
}
