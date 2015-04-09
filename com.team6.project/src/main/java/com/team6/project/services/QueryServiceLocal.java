package com.team6.project.services;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import javax.ejb.Local;
import javax.persistence.Query;


import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;

import com.team6.project.entities.Response;
import com.team6.project.entities.User;
import com.team6.project.entities.UserEquipment;
/**
 * The QueryService local interface.
 * 
 * @author Eoin Kernan
 * @author James Mc Ternan
 *
 */
@Local
public interface QueryServiceLocal {
	
	/**
	 * 
	 * @return
	 */
	public Collection<UserEquipment> getAllUserEquipment();
	
	public Collection<EventCause> findByIMSI(BigInteger IMSI);

	public User getUserByKey(String newUserId);
	
	public Collection<User> getAllUser();

	
	public Collection<Object[]> getDistinctEventByTac(Integer ue);
	
	public long countCallFailureByTac(Integer tac, Date fromDate, Date toDate);
			
	public Response countCallFailureByTacPOST(Integer tac, Date fromDate, Date toDate);
	
	public Collection<Object[]> getFailureCountAndDurationPerImsiByDate(Date startDate, Date endDate);

	public Collection<BaseData> findImsiByDate(Date firstDate, Date secondDate);
	
	public long countCallFailurePerImsiByDate(BigInteger imsi, Date startDate, Date endDate);

}
