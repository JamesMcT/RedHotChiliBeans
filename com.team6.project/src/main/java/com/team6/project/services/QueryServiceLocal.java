package com.team6.project.services;

import java.util.Collection;
import java.util.Date;

import javax.ejb.Local;

import com.team6.project.entities.Response;
import com.team6.project.entities.User;
import com.team6.project.entities.UserEquipment;
/**
 * The QueryService local interface.
 * 
 * @author Eoin Kernan
 *
 */
@Local
public interface QueryServiceLocal {
	
	public Collection<UserEquipment> getAllUserEquipment();
	
	public User getUserByKey(String newUserId);
	
	public Collection<User> getAllUser();
	
	public Collection<Object[]> getDistinctEventByTac(String ue);
	
	public long countCallFailureByTac(Integer tac, Date fromDate, Date toDate);
			
	public Response countCallFailureByTacPOST(Integer tac, Date fromDate, Date toDate);
	
}
