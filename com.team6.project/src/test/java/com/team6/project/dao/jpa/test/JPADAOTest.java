package com.team6.project.dao.jpa.test;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import com.team6.project.dao.BaseDataDAO;
import com.team6.project.dao.EventCauseDAO;
import com.team6.project.dao.FailureTypeDAO;
import com.team6.project.dao.OperatorCountryDAO;
import com.team6.project.dao.RecordDAO;
import com.team6.project.dao.UserDAO;
import com.team6.project.dao.UserEquipmentDAO;

public abstract class JPADAOTest {
    
    @EJB
    BaseDataDAO baseDataDAO;
    @EJB
    EventCauseDAO eventCauseDAO;
    @EJB
    FailureTypeDAO failureTypeDAO;
    @EJB
    OperatorCountryDAO operatorCountryDAO;
    @EJB
    UserEquipmentDAO userEquipmentDAO;
    @EJB
    RecordDAO recordDAO;
    @EJB
    UserDAO userDAO;

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
}
