package com.team6.project.services;

import java.math.BigInteger;
import java.util.Collection;

import javax.ejb.Local;
import javax.persistence.Query;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
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
	
	public Collection<EventCause> findByIMSI(BigInteger IMSI);
	
}
