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
        insertData();
    }

    private void insertData() throws Exception {
        userDAO.addUser(user);
    }
    
   @Test
    public void testEventCause() {
        User u = userDAO.getUserByKey(user.getUserId());
        assertEquals(u, user);
    }

}
