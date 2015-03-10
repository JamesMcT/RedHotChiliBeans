package com.team6.project.services.rest;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.team6.project.entities.BaseData;
import com.team6.project.services.BaseDataServiceLocal;

@Path("/basedata")
public class BaseDataRest {

	@Inject
	BaseDataServiceLocal baseDataServiceLocal;
	
	public BaseDataRest(){}

	@GET
	@Path("/datequery")
	@Produces(MediaType.APPLICATION_JSON)

	public Collection<BaseData> findImsiByDate(@QueryParam("firstDate") String firstDate, @QueryParam("secondDate") String secondDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	    Date first = null;
	    Date second = null;

			try {
				first = sdf.parse(firstDate);
				second = sdf1.parse(secondDate);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		System.out.println("1date" + first + "2date" + second);
		return baseDataServiceLocal.findImsiByDate(first,second);
	}
}