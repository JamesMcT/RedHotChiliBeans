package com.team6.project.dao.jpa.test;

import static org.junit.Assert.assertEquals;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.team6.project.entities.UserEquipment;

@RunWith(Arquillian.class)
public class JPAUserEquipmentDAOTest extends JPADAOTest{

    private UserEquipment userEquipment;

    @Before
    public void preparePersistenceTest() throws Exception {
        userEquipment = new UserEquipment(123, "", "", "", "", "", "", "", "");
        clear();
        insertData();
    }

    private void insertData() throws Exception {
        userEquipmentDAO.addUserEquipment(userEquipment);
    }
    
    private void clear() throws Exception {
        userEquipmentDAO.deleteUserEquipment(userEquipment);
    }

   
    @Test
    public void testUserEquipment() {
        UserEquipment ue = userEquipmentDAO.getUserEquipmentByKey(userEquipment
                .getKey());
        assertEquals(ue, userEquipment);
    }

  
}
