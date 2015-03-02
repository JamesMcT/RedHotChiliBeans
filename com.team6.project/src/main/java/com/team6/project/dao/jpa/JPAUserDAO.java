package com.team6.project.dao.jpa;

import javax.annotation.security.DeclareRoles;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.team6.project.dao.UserDAO;
import com.team6.project.entities.Response;
import com.team6.project.entities.User;

@Stateless
@Local
@DeclareRoles({ "administrator" })
public class JPAUserDAO implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Response addUser(User user) {
        Response response = new Response();
        if (em.find(User.class, user.getKey()) == null) {
            em.persist(user);
            response.setStatus(Response.Status.OK);
        } else {
            response.setStatus(Response.Status.ERROR);
            response.setDescription("User already exists");
        }
        return response;
    }

    @Override
    public Response updateUser(User user) {
        Response response = new Response();
        if (em.find(User.class, user.getKey()) != null) {
            em.merge(user);
            response.setStatus(Response.Status.OK);
        } else {
            response.setStatus(Response.Status.NOT_FOUND);
            response.setDescription("User not found");
        }
        return response;
    }

    @Override
    public User getUserByKey(String userId) {
        return em.find(User.class, userId);

    }

    @Override
    public Response deleteUser(User user) {
        Response response = new Response();
        if (em.find(User.class, user.getKey()) != null) {
            em.remove(user);
            response.setStatus(Response.Status.OK);
        } else {
            response.setStatus(Response.Status.NOT_FOUND);
            response.setDescription("User not found");
        }
        return response;

    }

}
