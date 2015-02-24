package com.team6.project.services;

import java.util.Collection;

import javax.ejb.Local;

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
	
}
