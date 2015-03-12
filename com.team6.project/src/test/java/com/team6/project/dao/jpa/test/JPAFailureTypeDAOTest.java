package com.team6.project.dao.jpa.test;

import static org.junit.Assert.assertEquals;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.team6.project.entities.FailureType;

@RunWith(Arquillian.class)
public class JPAFailureTypeDAOTest extends JPADAOTest{

    private FailureType failureType;

    @Before
    public void preparePersistenceTest() throws Exception {

        failureType = new FailureType(1, "desc Failure Type");
        clear();
        insertData();
    }

    private void insertData() throws Exception {
        failureTypeDAO.addFailureType(failureType);
    }

    private void clear() throws Exception {
        failureTypeDAO.deleteFailureType(failureType);
    }

    @Test
    public void testFailureType() {
        FailureType ft = failureTypeDAO.getFailureTypeByKey(failureType
                .getKey());
        assertEquals(ft, failureType);
    }

}
