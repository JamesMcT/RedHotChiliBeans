package com.team6.project.services.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DefaultValue;
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
import com.team6.project.services.QueryServiceLocal;

/**
 * Restful Service Class for managing the Network Management Engineer requests
 * 
 * @author Cristiana Conti
 */
@Path("/networkmanagement")
public class NetworkManagementRestService {

    @Inject
    QueryServiceLocal queryService;

    @Context
    private HttpServletResponse response;

    @GET
    @Path("/eventidcausecode/{userequipment}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Object[]> getDistinctEventByTac(
            @PathParam("userequipment") String userequipment) {
        try {
            Integer tac = Integer.parseInt(userequipment);
            return queryService.getDistinctEventByTac(tac);
        } catch (NumberFormatException e) {
            String message = e.getMessage();
            final Response response = Response.status(Status.BAD_REQUEST)
                    .entity(message).build();
            throw new WebApplicationException(response);
        }

    }

    @GET
    @Path("/failurecountandduration")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Object[]> getFailureCountAndDurationPerImsiByDate(

    @QueryParam("startDate") String dateString1,
            @QueryParam("endDate") String dateString2) {
        String message = "";

        if ("".equals(dateString1) || "".equals(dateString2)) {
            message = "Empty date strings not allowed";
            final Response response = Response.status(Status.BAD_REQUEST)
                    .entity(message).build();
            throw new WebApplicationException(response);
        }

        SimpleDateFormat sdf = new SimpleDateFormat(
                                                    JPABaseDataDAO.MYSQL_DATE_FORMAT);
        Date d1 = new Date();
        Date d2 = new Date();

        try {
            d1 = sdf.parse(dateString1);
            d2 = sdf.parse(dateString2);
        } catch (ParseException pe) {
            message = pe.getMessage();
            final Response response = Response.status(Status.BAD_REQUEST)
                    .entity(message).build();
            throw new WebApplicationException(response);
        }

        if (d1.after(d2)) {
            message = "Start-date can not be after end-date";
            final Response response = Response.status(Status.BAD_REQUEST)
                    .entity(message).build();
            throw new WebApplicationException(response);
        }

        if (d1.after(new Date(System.currentTimeMillis()))) {
            message = "Start-date can not be in the future";
            final Response response = Response.status(Status.BAD_REQUEST)
                    .entity(message).build();
            throw new WebApplicationException(response);
        }

        Collection<Object[]> c = queryService
                .getFailureCountAndDurationPerImsiByDate(d1, d2);

        if (!(c.size() > 0)) {
            message = String
                    .format("No results for given date range '%s'->'%s'.",
                            dateString1, dateString2);
            final Response response = Response.status(Status.NOT_FOUND)
                    .entity(message).build();
            throw new WebApplicationException(response);
        }

        return c;

    }

    
    
    @GET
    @Path("/toptenimsifailures")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Object[]> getTopTenFailuresByDate(

    @QueryParam("start") String dateString1,
            @QueryParam("end") String dateString2) {
        String message = "";

        if ("".equals(dateString1) || "".equals(dateString2)) {
            message = "Empty date strings not allowed";
            final Response response = Response.status(Status.BAD_REQUEST)
                    .entity(message).build();
            throw new WebApplicationException(response);
        }

        SimpleDateFormat sdf = new SimpleDateFormat(
                                                    JPABaseDataDAO.MYSQL_DATE_FORMAT);
        Date d1 = new Date();
        Date d2 = new Date();

        try {
            d1 = sdf.parse(dateString1);
            d2 = sdf.parse(dateString2);
        } catch (ParseException pe) {
            message = pe.getMessage();
            final Response response = Response.status(Status.BAD_REQUEST)
                    .entity(message).build();
            throw new WebApplicationException(response);
        }

        if (d1.after(d2)) {
            message = "Start-date can not be after end-date";
            final Response response = Response.status(Status.BAD_REQUEST)
                    .entity(message).build();
            throw new WebApplicationException(response);
        }

        if (d1.after(new Date(System.currentTimeMillis()))) {
            message = "Start-date can not be in the future";
            final Response response = Response.status(Status.BAD_REQUEST)
                    .entity(message).build();
            throw new WebApplicationException(response);
        }

        Collection<Object[]> c = queryService
                .getTopTenFailuresByDate(d1, d2);

        if (!(c.size() > 0)) {
            message = String
                    .format("No results for given date range '%s'->'%s'.",
                            dateString1, dateString2);
            final Response response = Response.status(Status.NOT_FOUND)
                    .entity(message).build();
            throw new WebApplicationException(response);
        }

        return c;

    }
}
