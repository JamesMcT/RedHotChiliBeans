package com.team6.project.services;

import java.util.Collection;
import java.util.Date;

import javax.ejb.Local;

import com.team6.project.entities.UserEquipment;
/**
 * The QueryService local interface.
 * 
 * @author Eoin Kernan
 * @author D14125306 - Sabee
 *
 */
@Local
public interface QueryServiceLocal {
	
	public Collection<UserEquipment> getAllUserEquipment();
	
	public long countCallFailureByTac(Integer tac, Date fromDate, Date toDate);
	
}
