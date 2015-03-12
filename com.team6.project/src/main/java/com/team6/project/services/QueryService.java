package com.team6.project.services;

import java.util.Collection;

import javax.ejb.Local;
import javax.inject.Inject;

import com.team6.project.dao.UserEquipmentDAO;
import com.team6.project.entities.UserEquipment;

/**
 * The QueryService EJB
 * 
 * @author Eoin Kernan
 *
 */
@Local(QueryServiceLocal.class)
public class QueryService implements QueryServiceLocal{

	@Inject
	private UserEquipmentDAO userEquipment;
	
	/**
	 * 
	 */
	public QueryService() {}

	@Override
	public Collection<UserEquipment> getAllUserEquipment() {
		return userEquipment.getAllUserEquipment();
	}

}
