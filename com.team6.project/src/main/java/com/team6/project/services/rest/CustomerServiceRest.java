package com.team6.project.services.rest;

import java.math.BigInteger;
import java.util.Collection;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

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

    @GET
    @Path("/{imsi}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<EventCause> findByImsi(@PathParam("imsi") BigInteger imsi) {

        return queryService.findByIMSI(imsi);

    }


    @GET
    @Path("/uniqueec/{imsi}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Object[]> getUniqueEventCauseByImsi(
            @PathParam("imsi") BigInteger imsi) {
        return queryService.getUniqueEventCauseByImsi(imsi);
    }
    /*
     * @GET
     * 
     * @Path("/{tac}")
     * 
     * @Produces(MediaType.APPLICATION_JSON) public long
     * countCallFailureByTac(@PathParam("tac") Integer tac){ return
     * queryService.countCallFailureByTac(tac); }
     */

}
