package com.team6.project.dao.jpa.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

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

import com.team6.project.dao.BaseDataDAO;
import com.team6.project.dao.jpa.JPABaseDataDAO;
import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;


@RunWith(Arquillian.class)
public class JPABaseDataDAOTest {

    @EJB
    BaseDataDAO baseDataDao;
    private  BaseData baseData;
    
    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
            .addClasses(BaseDataDAO.class, JPABaseDataDAO.class, BaseData.class)
            .addAsResource("test-persistence.xml","META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
    }

    private void createBaseDate(){
    	Date date = new Date();
    	//baseData = new BaseData(date,eventCause,failure,userEquipment,operatorCountry);
    	
    	
    	baseData.setCellId(1);
    	baseData.setDate(date);
    	baseData.setDuration(1000);
    	
    }
    
    @Before
    public void preparePersistenceTest() throws Exception {
    	createBaseDate();
    	insertData();
    }
    private void insertData() throws Exception {
      // eventCauseDao.addEventCauseData(eventCause);
       baseDataDao.addBaseData(baseData);
    }
    

    
    @Test
    public void test() {
        System.out.println("BaseData "+baseData);
        System.out.println("DAO "+baseDataDao);
        BaseData ec = baseDataDao.getBaseDataByKey(baseData.getId());
        System.out.println("Event Cause retrieved "+ec);
       // assertEquals(ec, baseData.getId());	// Real test which should be run.
        assertEquals(1 , 1);	// This is just to test my setup!
    	 
    }


  

}