package com.team6.project.dao.jpa;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;

import com.team6.project.dao.BaseDataDAO;
import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCausePK;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.OperatorCountryPK;
import com.team6.project.entities.Record;
import com.team6.project.entities.UserEquipment;



/**
 * @author James Mc Ternan
 * @Author Eoin Kernan
 *
 */
@Stateless
@Local
public class JPABaseDataDAO implements BaseDataDAO {

	public final static int BATCH_SIZE = 2000;
	public static final String MYSQL_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
	
	@PersistenceContext
	EntityManager em;

	@PersistenceContext
    private Session session;
	
	public static long count;
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
    @Override
	public Collection<BaseData> getAllBaseData() {
		Query q = em.createQuery("from BaseData");
		List<BaseData> result = q.getResultList();
		return result;
	}

	/**
	 * Return base data record via record id.
	 */
	@Override
	public BaseData getBaseDataByKey(Integer id) {
		Query q = em.createQuery("from BaseData where id = :code");
		q.setParameter("code", id);
		return (BaseData) q.getSingleResult();
	}

	/**
	 * Add new base data record to database.
	 */
	@Override
	public void addBaseData(BaseData baseData) {
		baseData.setId((int)count++);
		em.persist(baseData);
	}

	@Override
	public void addBaseDataCollection(Collection<BaseData> baseData){
		
		count = getBaseDataCount() + 1;
		
		session.beginTransaction();
		
		for(BaseData b:baseData){
			b.setId((int)count++);
			em.persist(b);
			
			if(count%BATCH_SIZE == 0){
				session.flush();
				session.clear();
			}
		}
		
		session.getTransaction().commit();
	}
	
	@Override
	public void deleteBaseData(BaseData baseData) {
		em.remove(baseData);
	}

	@Override
	public Collection<BaseData> findByImsi(BigInteger imsi) {
		Query q = em.createQuery("from BaseData where imsi = :code");
		q.setParameter("code", imsi);
		@SuppressWarnings("unchecked")
        List<BaseData> result = q.getResultList();
		return result;
	}

	@Override
	public Collection<BaseData> findByFailureType(FailureType failureType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<BaseData> findByUserEquipment(UserEquipment userEquipment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<BaseData> findByOperatorByMCC(Integer mcc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<BaseData> findByEventCause(EventCausePK eventCausePK) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<BaseData> findByOperatorCountryPK(
			OperatorCountryPK operatorCountryPK) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getBaseDataCount(){
		return (long)em.createNamedQuery("baseDataCount").getSingleResult();
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

    @Override
    public Collection<Object[]> getDistinctEventByTac(Integer ue) {
        Query q = em.createNamedQuery("eventCauseAndIdByTac");
        q.setParameter("userEquipment", ue);
        return q.getResultList();
        
    }
    
    @Override
    public Collection<Object[]> getFailureCountAndDurationPerImsiByDate(Date start, Date end){
    	
    	SimpleDateFormat sdf = new SimpleDateFormat(MYSQL_DATE_FORMAT);
    	String d1 = sdf.format(start);
    	String d2 = sdf.format(end);
    	
    	Query q = em.createNamedQuery("failureCountAndDurationPerImsiByDate");
    	q.setParameter("startDate", d1);
    	q.setParameter("endDate", d2);
    	
    	return q.getResultList();
    }
    
}
