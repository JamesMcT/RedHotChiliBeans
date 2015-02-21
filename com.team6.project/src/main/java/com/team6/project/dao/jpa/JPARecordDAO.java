package com.team6.project.dao.jpa;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import com.team6.project.dao.RecordDAO;
import com.team6.project.entities.BaseData;
import com.team6.project.entities.Record;

@Stateless
@Local
public class JPARecordDAO implements RecordDAO {

    @PersistenceContext
    private EntityManager em;
    
    public void addRecord(Record record){
        em.persist(record);
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
    public void addRecordSet(Collection<Record> recordSet) {
        //EntityTransaction tx =  em.getTransaction();
        //tx.begin();
        for(Record rec : recordSet){
            em.persist(rec);
        }
        //tx.commit();
        
    }

}

