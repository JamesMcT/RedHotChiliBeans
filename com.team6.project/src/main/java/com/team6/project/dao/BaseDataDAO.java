package com.team6.project.dao;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import javax.ejb.Local;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.EventCause;
import com.team6.project.entities.EventCausePK;
import com.team6.project.entities.FailureType;
import com.team6.project.entities.OperatorCountryPK;
import com.team6.project.entities.UserEquipment;

/**
 * BaseData Data Access Object.
 * <p>
 * The base data data access object interface. This interface will allow for the
 * basic CRUD operations [Create, Remove, Update, Delete] along with additional
 * abstract methods allowing for necessary queries to database.
 * 
 * @author John O Keeffe
 * @author Eoin Kernan
 * @author Cristiana Conti
 * @author Sabee
 */
@Local
public interface BaseDataDAO {

	/**
	 * Returns a collection of EventCause objects for a given Imsi.
	 * 
	 * @param imsi
	 *            passed value must be BigInteger
	 * @return Collection<EventCause> is returned.
	 */

	public Collection<EventCause> findByImsi(BigInteger imsi);

	/**
	 * Returns a collection of BaseData objects for a given FailureType.
	 * 
	 * @param failureType
	 * @return Collection<BaseData>
	 */
	// public Collection<BaseData> findByFailureType(FailureType failureType);

	/**
	 * 
	 * 
	 * @param userEquipment
	 * @return
	 */
	// public Collection<BaseData> findByUserEquipment(UserEquipment
	// userEquipment);

	/**
	 * Returns a collection of BaseData objects for a given in value
	 * representing market-operator country code(mcc)
	 * 
	 * @param mcc
	 * @return
	 */
	// public Collection<BaseData> findByOperatorByMCC(Integer mcc);

	/**
	 * Currently unimplemented.
	 * 
	 * @param operatorCountryPK
	 * @return Collection of BaseData objects for a given OperatorCountryPK.
	 */
	// public Collection<BaseData> findByOperatorCountryPK(
	// OperatorCountryPK operatorCountryPK);

	/**
	 * Find all BaseData records associated with a specific eventCause.
	 * 
	 * @param eventCausePK
	 * @return Collection of BaseData objects for a given eventCause primary key
	 *         object.
	 */
	// public Collection<BaseData> findByEventCause(EventCausePK eventCausePK);

	/**
	 * Returns IMSI, FailureCode and Count from a BaseData records who's date is
	 * between the first and second date.
	 * 
	 * @param firstDate
	 * @param secondDate
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> findImsiByDate(Date firstDate, Date secondDate);

	/**
	 * Returns all Basedata objects.
	 * 
	 * @return Collection<BaseData>
	 */
	public Collection<BaseData> getAllBaseData();

	/**
	 * Returns a single BaseData records for a given id.
	 * 
	 * @param id
	 * @return
	 */
	public BaseData getBaseDataByKey(Integer id);

	/**
	 * Adds a BaseData records. No return type. Accepts a baseData record.
	 * 
	 * @param baseData
	 */
	public void addBaseData(BaseData baseData);

	/**
	 * Adds multiple baseData records. Accepts a collection of BaseData records.
	 * Implements batch processing.
	 * 
	 * @param baseData
	 */
	public void addBaseDataCollection(Collection<BaseData> baseData);

	/**
	 * Remove baseData record. Accepts BaseData object which is to be removed.
	 * 
	 * @param baseData
	 */
	public void deleteBaseData(BaseData baseData);

	/**
	 * Counts number of records currently within BaseData.
	 * 
	 * @return count (long) of all BaseData records.
	 */
	public long getBaseDataCount();

	/**
	 * Gets each distinct failure for a given TAC. This TAC value is a Integer
	 * value which represents a specific piece of hardware (e.g Phone Model
	 * etc).
	 * 
	 * @param ue
	 * @return collection of object [EventCause Object and Count] from BaseData
	 *         for a given User Equipment id value = TAC. This id must be an
	 *         Integer.
	 */
	public Collection<Object[]> getDistinctEventByTac(Integer ue);

	/**
	 * Counts number of records for a given TAC value. A TAC represents a
	 * specific device (e.g each handset model will have a specific TAC value.)
	 * This TAC value is an Integer value.
	 * 
	 * @param tac
	 * @param fromDate
	 * @param toDate
	 * 
	 * @return long Return number of failure caused by tac in a given time
	 *         period.
	 */
	public long countCallFailureByTac(Integer tac, Date fromDate, Date toDate);

	/**
	 * Returns IMSI, Count and duration per IMSI for a given date range.
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public Collection<Object[]> getFailureCountAndDurationPerImsiByDate(
			Date start, Date end);

	/**
	 * This method will get 10 IMSI's which have the greatest number of failures
	 * for a given date period.
	 * 
	 * @param start
	 * @param end
	 * @return IMSI and count for the top ten IMSI's with most failures /
	 *         records within BaseData for a given time period.
	 */
	public Collection<Object[]> getTopTenFailuresByDate(Date start, Date end);

	/**
	 * Gets all Imsi's which are affected by a specific failure code.
	 * 
	 * @param fc
	 * @return Imsi(BigInteger) and failureDate(Date) are returned.
	 */
	public Collection<Object[]> getImsiByFailureCode(Integer fc);

	/**
	 * Returns all IMSI values from BaseData records.
	 * 
	 * @return a collection of BigInteger objects.
	 */
	public Collection<BigInteger> getAllImsi();

	/**
	 * Counts all call failures per IMSI within a specific date range.
	 * 
	 * @param imsi
	 *            BigInteger value which uniquely identifies a specific handset.
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public Collection<BaseData> countCallFailurePerImsiByDate(BigInteger imsi,
			Date startDate, Date endDate);

	/**
	 * Gets unique event cause and count associated with a specific IMSI. IMSI
	 * value represents a unique handset. Event cause provides an indication of
	 * the cause of the call failure.
	 * 
	 * @param imsi
	 * @return All the unique EventCause objects and the number of their
	 *         occurrences associated to a specific IMSI.
	 */
	public Collection<Object[]> getUniqueEventCauseByImsi(BigInteger imsi);

	/**
	 * Get top 10 operators who have the largest failure count for a give time
	 * period.
	 * 
	 * @param fromDate
	 * @param toDate
	 * 
	 * @return Collection<[]> containing Operator Id and Cell ID for ten
	 *         operators with the most failures occurring within the specified
	 *         time period.
	 * 
	 */
	public Collection<Object[]> getTOP10MarketOperatorCellByDate(Date fromDate,
			Date toDate);

}
