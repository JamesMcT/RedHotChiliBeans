package com.team6.project.dao.jpa;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.team6.project.dao.RecordDAO;
import com.team6.project.entities.BaseData;
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
    
    public static int count;
    
    public void addRecord(Record record){
    	record.setId(count++);
        em.persist(record);
        
    }

    public void addRecordCollection(Collection<Record> records){
    	
    	Transaction tx = session.beginTransaction();
		
    	for(Record r:records){
			r.setId(count++);
			em.persist(r);
			
			if(count%50 == 0){
				session.flush();
				session.clear();
			}
		}
    	
    	tx.commit();
    }
    
    @Override
    public void deleteRecord(Record record) {
        em.remove(record);
        
    }

    @Override
    public Record getRecordByKey(int id) {
        return em.find(Record.class, id);
        
    }

}

