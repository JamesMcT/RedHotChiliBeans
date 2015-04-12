package com.team6.project.services.rest;

import java.util.Collection;
import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.FailureType;
import com.team6.project.services.QueryServiceLocal;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * 
 * @author Sabee D14125306
 *
 */

@Path("/supportengineer")
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
        String message = "";
        fromDate.setTime(lFromDate);
        toDate.setTime(lToDate);
        if (fromDate.after(toDate)) {
            message = "Start-date can not be after end-date";
            final Response response = Response.status(Status.BAD_REQUEST)
                    .entity(message).build();
            throw new WebApplicationException(response);
        }
        if (fromDate.after(new Date(System.currentTimeMillis()))) {
            message = "Start-date can not be in the future";
            final Response response = Response.status(Status.BAD_REQUEST)
                    .entity(message).build();
            throw new WebApplicationException(response);
        }
        Long count = queryService.countCallFailureByTac(tac, fromDate, toDate);
        System.err.println("The value of count is..." + count);
        if (count == null || count == 0) {
            message = String.format(
                    "No results for given date range '%s'->'%s'.",
                    fromDate.toString(), toDate.toString());
            final Response response = Response.status(Status.NOT_FOUND)
                    .entity(message).build();
            throw new WebApplicationException(response);
        }
        return count;
    }

    @GET
    @Path("/datequery")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Object[]> findImsiByDate(
            @QueryParam("firstDate") long lfirstDate,
            @QueryParam("secondDate") long lsecondDate) {
        System.err.println("the first param is.." + lfirstDate);
        Date fromDate = new Date();
        Date toDate = new Date();
        String message = "";
        fromDate.setTime(lfirstDate);
        toDate.setTime(lsecondDate);
        if (fromDate.after(toDate)) {
            message = "Start-date can not be after end-date";
            final Response response = Response.status(Status.BAD_REQUEST)
                    .entity(message).build();
            throw new WebApplicationException(response);
        }
        if (fromDate.after(new Date(System.currentTimeMillis()))) {
            message = "Start-date can not be in the future";
            final Response response = Response.status(Status.BAD_REQUEST)
                    .entity(message).build();
            throw new WebApplicationException(response);
        }
        Collection<Object[]> c = queryService.findImsiByDate(fromDate, toDate);
        if (!(c.size() > 0)) {
            message = String.format(
                    "No results for given date range '%s'->'%s'.",
                    fromDate.toString(), toDate.toString());
            final Response response = Response.status(Status.NOT_FOUND)
                    .entity(message).build();
            throw new WebApplicationException(response);
        }
        return c;
    }

    @GET
    @Path("/failurecode")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Object[]> getImsiByFailureCode(
            @QueryParam("failureCode") Integer failureCode) {
        String message = "";
        Collection<Object[]> failuretypequery = queryService
                .getImsiByFailureCode(failureCode);
        if (!(failuretypequery.size() > 0)) {
            message = String.format("No results for this failure code");
            final Response response = Response.status(Status.NOT_FOUND)
                    .entity(message).build();
            throw new WebApplicationException(response);
        }
        return failuretypequery;
    }

    @GET
    @Path("/failuretype")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<FailureType> getAllFailureTypes() {
        return queryService.getAllFailureTypes();
    }

}
