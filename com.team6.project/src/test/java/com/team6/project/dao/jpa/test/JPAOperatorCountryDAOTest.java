package com.team6.project.dao.jpa.test;

import static org.junit.Assert.assertEquals;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.team6.project.entities.OperatorCountry;

@RunWith(Arquillian.class)
public class JPAOperatorCountryDAOTest extends JPADAOTest{

    private OperatorCountry operatorCountry;

    @Before
    public void preparePersistenceTest() throws Exception {
        operatorCountry = new OperatorCountry(1, 2, "Country", "Operator");
        clear();
        insertData();
    }

    private void insertData() throws Exception {
        operatorCountryDAO.addOperatorCountry(operatorCountry);
    }

    private void clear() throws Exception {
        operatorCountryDAO.deleteOperatorCountry(operatorCountry);
    }

    @Test
    public void testOperatorCountry() {
        OperatorCountry oc = operatorCountryDAO
                .getOperatorCountryByKey(operatorCountry.getKey());
        assertEquals(oc, operatorCountry);
    }

}
