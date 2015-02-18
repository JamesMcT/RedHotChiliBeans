package com.team6.project.dao.jpa.test;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.team6.project.dao.jpa.JPAEventCauseDAO;
import com.team6.project.entities.EventCause;

@RunWith(Arquillian.class)
public class JPAEventCauseDAOTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class, "test.war")
                .addPackage(JPAEventCauseDAO.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    private  EventCause eventCause = new EventCause(1, 2, "desc");

    @EJB
    JPAEventCauseDAO eventCauseDao;
    
    @Before
    public void preparePersistenceTest() throws Exception {
    }

    @Test
    public void test() {
    }

  

}
