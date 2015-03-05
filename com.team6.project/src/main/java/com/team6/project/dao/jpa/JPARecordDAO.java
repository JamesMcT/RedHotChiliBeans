package com.team6.project.dao.jpa;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;

import com.team6.project.dao.RecordDAO;
import com.team6.project.entities.Record;

/**
 * 
 * @Author James Mc Ternan
 * @author Eoin Kernan
 *
 */
@Stateless
@Local
public class JPARecordDAO implements RecordDAO {

    @PersistenceContext
    private EntityManager em;
    
    @PersistenceContext
    private Session session;
    
    public static long count;
    
    public void addRecord(Record record){
    	record.setId((int)count++);
        em.persist(record);
        
    }

    @Override
    public void addRecordCollection(Collection<Record> records){
    	
    	count = getRecordCount() + 1;
    	
    	session.beginTransaction();
    	
    	for(Record r:records){
			r.setId((int)count++);
			em.persist(r);
			
			// Magic number, this is the batch size stated in hibernate.xml
			if(count % JPABaseDataDAO.BATCH_SIZE == 0){
				session.flush();
				session.clear();
			}
		}
    	
    	session.getTransaction().commit();
    }
    
    @Override
    public void deleteRecord(Record record) {
        em.remove(record);
        
    }

    @Override
    public Record getRecordByKey(int id) {
        return em.find(Record.class, id);
        
    }

	@Override
	public long getRecordCount() {
		return (long)em.createNamedQuery("erroneousRecordCount").getSingleResult();
	}

}

