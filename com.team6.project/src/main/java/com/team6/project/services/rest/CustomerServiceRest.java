package com.team6.project.services.rest;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.team6.project.dao.jpa.JPABaseDataDAO;
import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.Record;
import com.team6.project.services.QueryServiceLocal;

/**
 * First attempt at REST query. Basic hard-coded query "working" when URL is
 * entered in browser. Work in progress.
 * 
 * @author James
 *
 */

@Path("/IMSIEvent")
public class CustomerServiceRest {

	@Inject
	QueryServiceLocal queryService;

	public CustomerServiceRest() {
	}

	/**
	 * 
	 * @param imsi
	 * @return
	 */
	@GET
	@Path("/{imsi}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<EventCause> findByImsi(@PathParam("imsi") BigInteger imsi) {

		return queryService.findByIMSI(imsi);

	}

	SimpleDateFormat sdf = new SimpleDateFormat(
			JPABaseDataDAO.MYSQL_DATE_FORMAT);
	Date d1 = new Date();
	Date d2 = new Date();

	/**
	 * 
	 * @param imsi
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@GET
	@Path("/countImsi/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<BaseData> countImsi(@QueryParam("imsi") BigInteger imsi,
			@QueryParam("startDate") long startDate, @QueryParam("endDate") long endDate) {
		System.out.println("Hello countImsi Entered..");
		
		Date start = new Date();
		Date end = new Date();

		start.setTime(startDate);
		end.setTime(endDate);

		return queryService.countCallFailurePerImsiByDate(imsi, start,
				end);

	}

}
