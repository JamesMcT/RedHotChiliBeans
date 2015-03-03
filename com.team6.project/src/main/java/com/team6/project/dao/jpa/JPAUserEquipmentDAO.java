package com.team6.project.dao.jpa;

import java.util.Collection;
import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.team6.project.dao.UserEquipmentDAO;
import com.team6.project.entities.UserEquipment;



/**
 * The JPA implementation of the UserEquipmentDAO.
 * 
 * @author James McTernan
 * @author Eoin Kernan
 *
 */

@Local
@Stateless
@DeclareRoles({ "administrator" })
public class JPAUserEquipmentDAO implements UserEquipmentDAO {

	@PersistenceContext
	private EntityManager em;

	
    @Override
	public Collection<UserEquipment> getAllUserEquipment() {
    	//Call a named query, defined within the UserEquipment entity class, here.
    	return em.createNamedQuery("allUserEquipment").getResultList();
	}

	/**
	 * 
	 */
	@Override
	public UserEquipment getUserEquipmentByKey(Integer tac) {
		Query q = em.createQuery("from UserEquipment where tac = :code");
		q.setParameter("code", tac);
		return (UserEquipment) q.getSingleResult();
	}

	/**
	 * 
	 */
	@Override
	public void addUserEquipment(UserEquipment userEquipment) {
		em.persist(userEquipment);

	}

	/**
	 * 
	 */
	@Override
	public void updateUserEquipment(UserEquipment userEquipment) {
		em.merge(userEquipment);

	}

	/**
	 * 
	 */
	@Override
	public void deleteUserEquipment(UserEquipment userEquipment) {
		em.remove(userEquipment);

	}
	
// Unused May be required at a later stage
//	/**
//	 * 
//	 */
//	@Override
//	public void deleteByTac(Integer tac) {
//		Query q = em.createQuery("from UserEquipment where tac = :code");
//		q.setParameter("code", tac);
//		List<UserEquipment> result = q.getResultList();
//		em.remove(result.get(0));
//
//	}

	// @Override
	// public void deleteAll() {
	// // TODO Auto-generated method stub
	//
	// }

}
