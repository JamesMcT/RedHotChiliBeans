package com.team6.project.dao.jpa;

import javax.annotation.security.DeclareRoles;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.team6.project.dao.UserDAO;
import com.team6.project.entities.User;
@Stateless
@Local
@DeclareRoles({"administrator"})
public class JPAUserDAO implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addUser(User user) {
        if (em.find(User.class, user.getUserId()) == null) {
            em.persist(user);
        }
        else{
            em.merge(user);
        }
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public User getUserByKey(String userId) {
       return em.find(User.class, userId);

    }

    @Override
    public void daleteUser(User user) {
        em.remove(user);

    }

}
