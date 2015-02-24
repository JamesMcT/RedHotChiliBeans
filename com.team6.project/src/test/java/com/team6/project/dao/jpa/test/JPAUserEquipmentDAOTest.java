package com.team6.project.dao.jpa.test;

import static org.junit.Assert.assertEquals;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.team6.project.dao.UserEquipmentDAO;
import com.team6.project.entities.UserEquipment;

@RunWith(Arquillian.class)
public class JPAUserEquipmentDAOTest {

    
   @EJB
    UserEquipmentDAO userEquipmentDao;

    private UserEquipment userEquipment;

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class, "test.war")
                .addPackages(true, "com.team6.project.dao",
                             "com.team6.project.dao.jpa",
                             "com.team6.project.entities")
                .addAsResource("test-persistence.xml",
                               "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE,
                                     ArchivePaths.create("beans.xml"));
    }

    @Before
    public void preparePersistenceTest() throws Exception {
        userEquipment = new UserEquipment(123, "", "", "", "", "", "", "", "");
        clear();
        insertData();
    }

    private void insertData() throws Exception {
        userEquipmentDao.addUserEquipment(userEquipment);
    }
    
    private void clear() throws Exception {
        userEquipmentDao.deleteUserEquipment(userEquipment);
    }

   
    @Test
    public void testUserEquipment() {
        UserEquipment ue = userEquipmentDao.getUserEquipmentByKey(userEquipment
                .getKey());
        assertEquals(ue, userEquipment);
    }

  
}
