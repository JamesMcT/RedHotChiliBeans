package com.team6.project.services.rest;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.team6.project.entities.BaseData;
import com.team6.project.services.BaseDataServiceLocal;


@Path("/basedata")
public class BaseDataRest {

	@Inject
	BaseDataServiceLocal service;

	public BaseDataRest() {
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<BaseData> findImsiByDates(String neVersion) {
		return service.findImsiByDates(neVersion);
	}

}