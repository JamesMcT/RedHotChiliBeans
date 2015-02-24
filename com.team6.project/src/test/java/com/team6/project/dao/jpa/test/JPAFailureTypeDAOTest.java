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

import com.team6.project.dao.FailureTypeDAO;
import com.team6.project.entities.FailureType;

@RunWith(Arquillian.class)
public class JPAFailureTypeDAOTest {

    @EJB
    FailureTypeDAO failureTypeDao;

    private FailureType failureType;

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

        failureType = new FailureType(1, "desc Failure Type");
        clear();
        insertData();
    }

    private void insertData() throws Exception {
        failureTypeDao.addFailureType(failureType);
    }

    private void clear() throws Exception {
        failureTypeDao.deleteFailureType(failureType);
    }

    @Test
    public void testFailureType() {
        FailureType ft = failureTypeDao.getFailureTypeByKey(failureType
                .getKey());
        assertEquals(ft, failureType);
    }

}
