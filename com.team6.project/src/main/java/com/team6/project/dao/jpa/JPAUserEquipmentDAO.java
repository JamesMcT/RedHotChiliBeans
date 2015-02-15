package com.team6.project.dao.jpa;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.team6.project.dao.UserEquipmentDAO;
import com.team6.project.entities.UserEquipment;

/**
 * @author Sabee
 * 
 */

@Stateless
@Local
public class JPAUserEquipmentDAO implements UserEquipmentDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<UserEquipment> getRecords() {
		Query q = em.createQuery("from UserEquipment");
		return q.getResultList();		
	}

	@Override
	public void addNewUserEquipmentDataSet(UserEquipment userEquipment) {
		em.persist(userEquipment);
	}

	@Override
	public void updateUserEquipment(UserEquipment userEquipment) {
		em.merge(userEquipment);
	}

	@Override
	public UserEquipment findByTac(Integer tac) {
		Query q = em.createQuery("from EquipmentType c where c.tac like :tac")
				.setParameter("tac", tac);
		return null;
	}

	@Override
	public void deleteByTac(Integer tac) {
		Query q = em.createQuery("delete from EquipmentType where tac=dTac like :dTac")
				.setParameter("dTac", tac);		
	}

	@Override
	public void deleteAll() {
		Query q = em.createQuery("delete from EquipmentType where tac>=0");
		
	}

}
