package com.team6.project.services;

import java.math.BigInteger;
import java.util.Collection;

import javax.ejb.Local;
import javax.inject.Inject;
import javax.persistence.NamedQuery;

import com.mysql.jdbc.util.BaseBugReport;
import com.team6.project.dao.BaseDataDAO;
import com.team6.project.dao.UserDAO;
import com.team6.project.dao.UserEquipmentDAO;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;

import com.team6.project.entities.User;

import com.team6.project.entities.UserEquipment;

/**
 * The QueryService EJB
 * 
 * @author Eoin Kernan
 * @author James
 *
 */
@Local(QueryServiceLocal.class)
public class QueryService implements QueryServiceLocal{

	@Inject
	private UserEquipmentDAO userEquipment;
	@Inject
	private BaseDataDAO baseData;
	
	@Inject
    private UserDAO user;
	
	public QueryService() {}

	@Override
	public Collection<UserEquipment> getAllUserEquipment() {
		return userEquipment.getAllUserEquipment();
	}

	
	@Override
	public Collection<EventCause> findByIMSI(BigInteger IMSI) {
		return baseData.findByImsi(IMSI);
	}


    @Override
    public User getUserByKey(String newUserId) {
        return user.getUserByKey(newUserId);
    }

    @Override
    public Collection<User> getAllUser() {
        return user.getAllUser();
    }


}
