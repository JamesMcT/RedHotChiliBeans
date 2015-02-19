package com.team6.project.dao.jpa.test;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Collection;
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
import com.team6.project.dao.EventCauseDAO;
import com.team6.project.dao.FailureTypeDAO;
import com.team6.project.dao.OperatorCountryDAO;
import com.team6.project.dao.UserEquipmentDAO;
import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.UserEquipment;


@RunWith(Arquillian.class)
public class JPABaseDataDAOTest {

    @EJB
    BaseDataDAO baseDataDao;
    @EJB
    EventCauseDAO eventCauseDAO;
    @EJB
    FailureTypeDAO failureTypeDAO;
    @EJB
    OperatorCountryDAO operatorCountryDAO;
    @EJB
    UserEquipmentDAO userEquipmentDAO;
    
    private BaseData baseData;
    private EventCause eventCause;
    private FailureType failureType;
    private OperatorCountry operatorCountry;
    private UserEquipment userEquipment;
    
    /*
     *     @Deployment
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
     * 
     * 
     */
    
    
    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackages(true, "com.team6.project.dao",
                        "com.team6.project.dao.jpa",
                        "com.team6.project.entities")
            .addAsResource("test-persistence.xml","META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
    }


    @Before
    public void preparePersistenceTest() throws Exception {
    	createBaseDate();
    	clear();
    	insertData();
    }
    
    
    private void insertData() throws Exception {
    	operatorCountryDAO.addOperatorCountry(operatorCountry);
    	eventCauseDAO.addEventCauseData(eventCause);
    	failureTypeDAO.addFailureType(failureType);
    	userEquipmentDAO.addUserEquipment(userEquipment);
//    	
    	baseDataDao.addBaseData(baseData);
    }
    
    private void clear() throws Exception {
        baseDataDao.deleteBaseData(baseData);
        operatorCountryDAO.deleteOperatorCountry(operatorCountry);
        eventCauseDAO.deleteEventCause(eventCause);
        failureTypeDAO.deleteFailureType(failureType);
        userEquipmentDAO.deleteUserEquipment(userEquipment);
      
    }
    
    private void createBaseDate(){
    	
    	Date date = new Date();
    	System.out.println(date);
    	BigInteger b = new BigInteger("1234");
    	operatorCountry= new OperatorCountry(1, 2, "Country", "Operator");
    	eventCause = new EventCause(1, 2, "desc Event Cause");
    	failureType = new FailureType(1, "desc Failure Type");
    	userEquipment = new UserEquipment(123, "a", "a", "a", "a", "a", "a", "a", "a");
    	
    	baseData = new BaseData();
    	baseData.setDate(date);
    	baseData.setEventCause(eventCause);
    	baseData.setCellId(4);
    	baseData.setDuration(1000);
//    	baseData.setId(1);
    	baseData.setFailure(failureType);
    	baseData.setHier321Id(b);
    	baseData.setHier32Id(b);
    	baseData.setHier3Id(b);
    	baseData.setImsi(b);
    	baseData.setOperatorCountry(operatorCountry);
    	baseData.setNeVersion("12g");
    	baseData.setUserEquipment(userEquipment);
    }

    
    @Test
    public void test() {
    	
        System.err.println("BaseData "+baseData);
        System.err.println("DAO "+baseDataDao);
        BaseData bd = baseDataDao.getBaseDataByKey(baseData.getId());
        System.err.println("Event Cause retrieved "+bd.getEventCause());
        assertEquals(bd, baseData);	// Real test which should be run.
  
    }


  

}