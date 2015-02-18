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
import com.team6.project.entities.Record;
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
		em.merge(baseData);
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

	public static void fillData(Record record, BaseData baseData){
        baseData.setCellId(record.getCellId());
        baseData.setDuration(record.getDuration());
        baseData.setImsi(record.getImsi());
        baseData.setNeVersion(record.getNeVersion());
        baseData.setHier3Id(record.getHier3Id());
        baseData.setHier32Id(record.getHier32Id());
        baseData.setHier321Id(record.getHier321Id());
    }
    
    
    
    

}
