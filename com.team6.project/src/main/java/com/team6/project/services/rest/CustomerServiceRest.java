package com.team6.project.services.rest;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.team6.project.dao.jpa.JPABaseDataDAO;
import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
import com.team6.project.services.QueryServiceLocal;

/**
 * 
 * @author James
 * @author Cristiana
 */

@Path("/customerservice")
public class CustomerServiceRest {


	@Inject
	QueryServiceLocal queryService;
    @Context
    private HttpServletResponse response;

	public CustomerServiceRest() {
	}

	/**
	 * Finding the events caused by a given imsi
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

//	SimpleDateFormat sdf = new SimpleDateFormat(
//			JPABaseDataDAO.MYSQL_DATE_FORMAT);
//	Date d1 = new Date();
//	Date d2 = new Date();

	/**
	 * 
	 * Counting the failures affected by a given imsi in a given time period
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
		String message = "";
		
		Date start = new Date();
		Date end = new Date();

		start.setTime(startDate);
		end.setTime(endDate);
		
		if (start.after(end)) {
            message = "Start-date can not be after end-date";
            final Response response = Response.status(Status.BAD_REQUEST)
                    .entity(message).build();
            throw new WebApplicationException(response);
        }

        if (start.after(new Date(System.currentTimeMillis()))) {
            message = "Start-date can not be in the future";
            final Response response = Response.status(Status.BAD_REQUEST)
                    .entity(message).build();
            throw new WebApplicationException(response);
        }


        Collection<BaseData> c = queryService.countCallFailurePerImsiByDate(imsi, start,
				end);
        
        if (!(c.size() > 0)) {
            message = String.format(
                    "No results for given date range '%s'->'%s'.",
                    start.toString(), end.toString());
            final Response response = Response.status(Status.NOT_FOUND)
                    .entity(message).build();
            throw new WebApplicationException(response);
        }

        return c;

	}

	
	 /**
     * 
     * Finding the event caused by a given imsi
     * 
     * @param imsi
     * @return
     */
	
    @GET
    @Path("/uniqueec/{imsi}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Object[]> getUniqueEventCauseByImsi(
            @PathParam("imsi") BigInteger imsi) {
        return queryService.getUniqueEventCauseByImsi(imsi);
    }


}
