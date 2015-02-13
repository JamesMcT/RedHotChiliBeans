package com.team6.project.dao.jpa;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.team6.project.entities.BaseData;

@Stateless
@Local
public class JPABaseDataDAO {

    @PersistenceContext
    EntityManager em;
    
    public void persist(BaseData baseData){
        em.persist(baseData);
    }

}
