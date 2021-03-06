package com.team6.project.dao.jpa.test;

import static org.junit.Assert.assertEquals;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.team6.project.entities.User;

@RunWith(Arquillian.class)
public class JPAUserDAOTest extends JPADAOTest{
    
    private User user;
    
    @Before
    public void preparePersistenceTest() throws Exception {
        user = new User("username", "password", "administrator");
        userDAO.addUser(user);
    }
    
   @Test
    public void testGetUser() {
        User u = userDAO.getUserByKey(user.getKey());
        assertEquals(u, user);
    }
   
/*   @Test
   public void testAddUser_OK() {
       User user1 = new User("username1", "password", "administrator");
       userDAO.addUser(user1);
       assertEquals(Response.Status.OK, response.getStatus());
   }
   
   @Test
   public void testAddUser_ERROR() {
       User user1 = new User("username", "password", "administrator");
       Response response = userDAO.addUser(user1);
       assertEquals(Response.Status.ERROR, response.getStatus());
   }
   
   @Test
   public void testUpdateUser_OK() {
       User user1 = new User("username", "password2", "administrator");
       Response response = userDAO.updateUser(user1);
       assertEquals(Response.Status.OK, response.getStatus());
       assertEquals(user1.getPassword(), userDAO.getUserByKey(user1.getKey()).getPassword());
   }
   
   @Test
   public void testUpdateUser_NOT_FOUND() {
       User user1 = new User("username2", "password2", "administrator");
       Response response = userDAO.updateUser(user1);
       assertEquals(Response.Status.NOT_FOUND, response.getStatus());
   }
   */

}
