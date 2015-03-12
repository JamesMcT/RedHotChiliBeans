package com.team6.project.services.rest;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.team6.project.entities.UserEquipment;
import com.team6.project.services.QueryServiceLocal;

/**
 * The UserEquipment Rest service
 * 
 * @author Eoin Kernan
 *
 */

@Path("/userequipment")
public class UserEquipmentRest {

	@Inject
	QueryServiceLocal queryService;
	
	public UserEquipmentRest()
	{}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<UserEquipment> getAllUserEquipment(){
		return queryService.getAllUserEquipment();
	}
}
