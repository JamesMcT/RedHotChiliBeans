package com.team6.project.services.rest;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.team6.project.entities.BaseData;
import com.team6.project.services.BaseDataServiceLocal;


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

@Path("/basedata")
public class SupportEngineerRestService {


	@Inject
	BaseDataServiceLocal baseDataServiceLocal;

	
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

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response countCallFailureByTac(ReqParam reqParam) {

		// String fromDate_s = "2013-01-11 17:15:00";
		// String toDate_s = "2013-01-11 17:16:00";

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

		return queryService.countCallFailureByTacPOST(tac, fromDate, toDate);

	}
	
		
		@GET
		@Path("/datequery")
		@Produces(MediaType.APPLICATION_JSON)
		public Collection<BaseData> findImsiByDate(
				@QueryParam("firstDate") String firstDate,
				@QueryParam("secondDate") String secondDate) {

			
			
			System.out.println("date as long1: "+ firstDate + "date as long2: "+ secondDate);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

			// Date first = new Date();
			// Date second = new Date();

			// first.setTime(firstDate);
			// second.setTime(secondDate);
			Date first = null;
			Date second = null;

			try {
				first = sdf.parse(firstDate);
				second = sdf1.parse(secondDate);

			} catch (ParseException | NullPointerException e) {
				System.out.println("null pointer:::" + e);
				e.printStackTrace();
			}

			
		/*8	
			Date first = new Date();		
			Date second = new Date();
			
			first.setTime(firstDate);
			second.setTime(secondDate);
		*/	
			System.out.println("1date" + first + "2date" + second);
			return baseDataServiceLocal.findImsiByDate(first, second);
		}
	}


