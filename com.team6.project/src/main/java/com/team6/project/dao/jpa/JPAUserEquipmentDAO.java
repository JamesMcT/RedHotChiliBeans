package com.team6.project.dao.jpa;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.team6.project.dao.UserEquipmentDAO;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.UserEquipment;

/**
 * 
 * @author James
 *
 */

@Local
@Stateless
public class JPAUserEquipmentDAO implements UserEquipmentDAO {

	@PersistenceContext
	private EntityManager em;
	
	
	/**
	 * 
	 */
	@Override
	public UserEquipment getRecord(Integer tac) {
		Query q = em.createQuery("from UserEquiptment where tac = :code");
		q.setParameter("code", 2);	
		List<UserEquipment> result = q.getResultList();
		return result.get(0);
	}

	
	/**
	 * 
	 */
	@Override
	public void addNewUserEquipmentDataSet(UserEquipment userEquipment) {
		em.persist(userEquipment);
		
	}

	
	/**
	 * 
	 */
	@Override
	public void updateUserEquipment(UserEquipment userEquipment) {
		UserEquipment ue = getRecord(userEquipment.getTac());
		ue.setAccessCapability(userEquipment.getAccessCapability());
		ue.setInputMode(userEquipment.getInputMode());
		ue.setManufacturer(ue.getManufacturer());
		ue.setMarketingName(userEquipment.getMarketingName());
		ue.setModel(userEquipment.getMarketingName());
		ue.setOs(userEquipment.getOs());
		ue.setTac(userEquipment.getTac());
		ue.setType(userEquipment.getType());
		ue.setVendorName(userEquipment.getVendorName());
		
		em.merge(ue);

	}

	
	/**
	 * 
	 */
	@Override
	public void deleteRecord(Integer tac) {
		UserEquipment ue = getRecord(tac);
		em.remove(ue);

	}

}
