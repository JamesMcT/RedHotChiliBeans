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
import com.team6.project.services.QueryServiceLocal;

/**
 * 
 * @author Sabee D14125306
 *
 */

@Path("/basedata")
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
			@QueryParam("firstDate") String firstDate,
			@QueryParam("secondDate") String secondDate) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		Date first = null;
		Date second = null;

		try {
			first = sdf.parse(firstDate);
			second = sdf.parse(secondDate);

		} catch (ParseException | NullPointerException e) {
			e.printStackTrace();
		}

		return queryService.findImsiByDate(first, second);
	}
}
