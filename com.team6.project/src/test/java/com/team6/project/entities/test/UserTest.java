package com.team6.project.entities.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.team6.project.entities.User;

public class UserTest {

    private static User user1;


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        user1 = new User("user1", "password", "administrator");
    }
    
    @Test
    public void getKeyTest_True() {
        assertEquals(user1.getKey(), user1.getUserId());
    }
    
    @Test
    public void toStringTest() {
        assertEquals(user1.toString(), "UserId : user1 Password : password User Role : administrator");
    }
    
    @Test
    public void hashCodeTest_Equal(){
        User other = new User("user1", "password", "administrator");
        assertEquals(other.hashCode(), user1.hashCode());
    }
    @Test
    public void hashCodeTest_NotEqual(){
        User other = new User("user2", "password", "administrator");
        assertFalse(other.hashCode() == user1.hashCode());
    }
    
    
    @Test
    public void equalsTrueTest() {
        User other = new User("user1", "password", "administrator");
        assertTrue(user1.equals(other));
    }
    @Test
    public void equalsFalseTest_1() {
        User other = new User("user2", "password", "administrator");
        assertFalse(user1.equals(other));
    }
    
    @Test
    public void equalsFalseTest_2() {
        User other = new User(null, "password", "administrator");
        assertFalse(other.equals(user1));
    }
    
    @Test
    public void equalsFalseTest_3() {
        User other = null;
        assertFalse(user1.equals(other));
    }
      
}