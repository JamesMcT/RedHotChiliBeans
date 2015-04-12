package com.team6.project.dao.jpa.test;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import javax.validation.constraints.AssertTrue;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.OperatorCountry;
import com.team6.project.entities.UserEquipment;

@RunWith(Arquillian.class)
public class JPABaseDataDAOTest extends JPADAOTest {

	private static BaseData baseData;
	private static EventCause eventCause;
	private static FailureType failureType;
	private static OperatorCountry operatorCountry;
	private static UserEquipment userEquipment;
	private static boolean isPopulated = false;

	@Before
	public void populateDB() throws Exception {
		if (!isPopulated) {
			createBaseDate();
			clear();
			insertData();
			isPopulated = true;
		}
	}

	@Test
	public void test() {
		BaseData bd = baseDataDAO.getBaseDataByKey(baseData.getId());
		assertEquals(bd, baseData); // Real test which should be run.
	}

	/*
	 * @Test public void test_getDistinctEventByTac() { List<Object[]>
	 * eventCauseCol = (List<Object[]>) baseDataDAO.getDistinctEventByTac(123);
	 * System.out.println(eventCauseCol.get(0)[0]
	 * +" and ......"+eventCauseCol.get(0)[1]);
	 * assertFalse(eventCauseCol.isEmpty()); }
	 */

	@Test
	// S
	public void countCallFailureByTacTest() {
		Date fromDate = new Date();
		Date toDate = new Date();
		// set the time period +- 10 sec from now
		fromDate.setTime(fromDate.getTime() - 10000);
		toDate.setTime(toDate.getTime() + 10000);
		int tac = 123;

		long l_num = baseDataDAO.countCallFailureByTac(tac, fromDate, toDate);

		assertEquals(1, l_num);
	}

	@Test
	// S
	public void getTOP10MarketOperatorCellByDateTest() {
		Date fromDate = new Date();
		Date toDate = new Date();
		// set the time period +- 10 sec from now
		fromDate.setTime(fromDate.getTime() - 10000);
		toDate.setTime(toDate.getTime() + 10000);

		Collection<Object[]> resultSet = baseDataDAO
				.getTOP10MarketOperatorCellByDate(fromDate, toDate);
		for (Object[] o : resultSet) {
			System.err.println("BaseData is ..." + o[0].toString());
		}
		assertEquals(1, resultSet.size());

	}

	private void insertData() throws Exception {
		operatorCountryDAO.addOperatorCountry(operatorCountry);
		eventCauseDAO.addEventCauseData(eventCause);
		failureTypeDAO.addFailureType(failureType);
		userEquipmentDAO.addUserEquipment(userEquipment);

		baseDataDAO.addBaseData(baseData);
	}

	private void clear() throws Exception {
		baseDataDAO.deleteBaseData(baseData);
		operatorCountryDAO.deleteOperatorCountry(operatorCountry);
		eventCauseDAO.deleteEventCause(eventCause);
		failureTypeDAO.deleteFailureType(failureType);
		userEquipmentDAO.deleteUserEquipment(userEquipment);

	}

	private void createBaseDate() {

		Date date = new Date();
		BigInteger b = new BigInteger("1234");
		operatorCountry = new OperatorCountry(1, 2, "Country", "Operator");
		eventCause = new EventCause(1, 2, "desc Event Cause");
		failureType = new FailureType(1, "desc Failure Type");
		userEquipment = new UserEquipment(123, "a", "a", "a", "a", "a", "a",
				"a", "a");

		baseData = new BaseData();
		baseData.setDate(date);
		baseData.setEventCause(eventCause);
		baseData.setCellId(4);
		baseData.setDuration(1000);
		baseData.setFailure(failureType);
		baseData.setHier321Id(b);
		baseData.setHier32Id(b);
		baseData.setHier3Id(b);
		baseData.setImsi(b);
		baseData.setOperatorCountry(operatorCountry);
		baseData.setNeVersion("12g");
		baseData.setUserEquipment(userEquipment);
	}

	/**
	 * Test that callFailurePerImsiByDate returns the expected results. start
	 * date has been set to 10 seconds before current time and endDate has been
	 * set to set current time plus ten seconds.
	 */
	@Test
	public void testcountCallFailurePerImsiByDate() {
		Date fromDate = new Date();
		Date toDate = new Date();
		// set the time period +- 10 sec from now
		fromDate.setTime(fromDate.getTime() - 10000);
		toDate.setTime(toDate.getTime() + 10000);
		BigInteger imsi = new BigInteger("1234");

		Collection<BaseData> result = baseDataDAO
				.countCallFailurePerImsiByDate(imsi, fromDate, toDate);
		
		assertEquals(1, result.size());
	}
}