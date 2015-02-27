package com.team6.project.services;

import java.util.Collection;
import java.util.Date;

import javax.ejb.Local;
import javax.inject.Inject;

import com.team6.project.dao.BaseDataDAO;
import com.team6.project.dao.UserEquipmentDAO;
import com.team6.project.entities.UserEquipment;

/**
 * The QueryService EJB
 * 
 * @author Eoin Kernan
 * @author D14125306 - Sabee
 *
 */
@Local(QueryServiceLocal.class)
public class QueryService implements QueryServiceLocal{

	@Inject
	private UserEquipmentDAO userEquipment;
	@Inject
	private BaseDataDAO baseData;
	
	
	public QueryService() {}

	@Override
	public Collection<UserEquipment> getAllUserEquipment() {
		return userEquipment.getAllUserEquipment();
	}

	@Override
	public long countCallFailureByTac(Integer tac, Date fromDate, Date toDate) {		
		return baseData.countCallFailureByTac(tac, fromDate, toDate);
	}

}
