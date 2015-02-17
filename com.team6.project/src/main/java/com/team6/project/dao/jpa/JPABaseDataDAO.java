package com.team6.project.dao.jpa;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.team6.project.dao.BaseDataDAO;
import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.UserEquipment;
/**
 * 
 * @author James
 *
 */
@Stateless
@Local
@Default
public class JPABaseDataDAO implements BaseDataDAO {

    @PersistenceContext
    EntityManager em;
   
    public Collection<BaseData> getAllBaseData(){
    	Query query = em.createQuery("from BaseData");
		List<BaseData> results = query.getResultList();
		return results;
    }
    
    /**
     * Delete base data record via record id
     */
	@Override
	public BaseData getBaseDataRecord(Integer id) {
		Query q = em.createQuery("from BaseData where id = :code");
		q.setParameter("code", id );	
		List<BaseData> result = q.getResultList();
		return result.get(0);
	}
	
	
	/**
	 * 
	 */
	@Override
	public void addNewBaseDataSet(BaseData baseData) {
		em.persist(baseData);
	}

	
	/**
	 * 
	 */
	@Override
	public void updateBaseData(BaseData baseData) {
		BaseData bd = getBaseDataRecord(baseData.getId());
		bd.setCellId(baseData.getCellId());
		bd.setDate(baseData.getDate());
		bd.setDuration(baseData.getDuration());
		bd.setEventCause(baseData.getEventCause());
		bd.setFailure(baseData.getFailure());
		bd.setHier321Id(baseData.getHier321Id());
		bd.setHier32Id(baseData.getHier32Id());
		bd.setHier3Id(baseData.getHier3Id());
		bd.setId(baseData.getId());
		bd.setImsi(baseData.getImsi());
		bd.setNeVersion(baseData.getNeVersion());
		bd.setOperatorCountry(baseData.getOperatorCountry());
		bd.setUserEquipment(baseData.getUserEquipment());
		
		em.merge(bd);
	}

	@Override
	public void deleteBaseDataRecord(Integer id) {
		BaseData bd = getBaseDataRecord(id);
		em.remove(bd);
		
		/* Alternative solution
		
		*		Query q = em.createQuery("from BaseData where id = :code");
		q.setParameter("code", id );	
		List<BaseData> result = q.getResultList();
		em.remove(result.get(0));
		*/
		
		
	}


    
    
    
    

}
