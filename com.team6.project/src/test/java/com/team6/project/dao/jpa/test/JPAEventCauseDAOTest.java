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

import com.team6.project.dao.EventCauseDAO;
import com.team6.project.dao.jpa.JPAEventCauseDAO;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.EventCausePK;

@RunWith(Arquillian.class)
public class JPAEventCauseDAOTest {

    @EJB
    EventCauseDAO eventCauseDao;
    private  EventCause eventCause;
    
    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
            .addClasses(EventCauseDAO.class, JPAEventCauseDAO.class, EventCause.class, EventCausePK.class)
            .addAsResource("test-persistence.xml","META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
    }

    
    @Before
    public void preparePersistenceTest() throws Exception {
        eventCause = new EventCause(1, 2, "desc");
        insertData();
    }
    private void insertData() throws Exception {
       eventCauseDao.addNewEventCauseDataSet(eventCause);
    }
    
    @Test
    public void test() {
        System.err.println("EventCause "+eventCause);
        System.err.println("DAO "+eventCauseDao);
        EventCause ec = eventCauseDao.getEventCauseByKey(eventCause.getKey());
        System.err.println("Event Cause retrieved "+ec);
        assertEquals(ec , eventCause);
    }

  

}
