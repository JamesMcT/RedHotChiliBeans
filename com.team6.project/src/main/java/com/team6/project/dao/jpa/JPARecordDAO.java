package com.team6.project.dao.jpa;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.team6.project.entities.Record;

@Stateless
@Local
public class JPARecordDAO {

    @PersistenceContext
    private EntityManager em;
    
    public void persist(Record record){
        em.persist(record);
    }

}
