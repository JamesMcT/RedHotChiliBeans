package com.team6.project.dao.jpa;

import java.util.Collection;

import javax.annotation.security.DeclareRoles;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.team6.project.dao.UserDAO;
import com.team6.project.entities.Response;
import com.team6.project.entities.User;


/**
 * 
 * @author Cristiana Conti
 */
@Stateless
@Local
@DeclareRoles({ "administrator" })
public class JPAUserDAO implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addUser(User user) {
        em.persist(user);
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
    public Collection<User> getAllUser() {
        return em.createNamedQuery("User.allUser").getResultList();
    }

    /*
     * @Override public Response deleteUser(User user) { Response response = new
     * Response(); if (em.find(User.class, user.getKey()) != null) {
     * em.remove(user); response.setStatus(Response.Status.OK); } else {
     * response.setStatus(Response.Status.NOT_FOUND);
     * response.setDescription("User not found"); } return response;
     * 
     * }
     */

}