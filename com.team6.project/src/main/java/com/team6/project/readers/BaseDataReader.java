package com.team6.project.readers;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.IDescription;
import com.team6.project.entities.Record;
import com.team6.project.services.DataImportServiceLocal;
import com.team6.project.validators.AllTrueCompositeValidator;
import com.team6.project.validators.DateValidator;
import com.team6.project.validators.EventCauseValidator;
import com.team6.project.validators.FailureTypeValidator;
import com.team6.project.validators.IValidator;
import com.team6.project.validators.OperatorCountryValidator;
import com.team6.project.validators.UserEquipmentValidator;

/**
 * Reads rows in sheet called Base Data. Create the Record object. 
 * 
 * @author Cristiana Conti
 * @Author Eoin Kernan
 */
public class BaseDataReader extends Reader {

	private static final String NAME = "Base Data";

	@Override
	public void processExcelFile(DataImportServiceLocal service) {
		HSSFSheet sheet = service.getSheet(NAME);
		IValidator validator = createValidator();

		List<BaseData> dataList = new ArrayList<BaseData>();
		List<Record> recordList = new ArrayList<Record>();

		long beginTime = System.currentTimeMillis();
		readerLogger.warn("BaseDataReader: Begin reading BaseData");

		while (currentRow <= sheet.getLastRowNum()) {

			Record record = read(sheet);
			BaseData baseData = new BaseData();

			boolean isValid = validator.isValid(record, baseData, service);
			if (isValid) {
				dataList.add(baseData);

			} else {
				recordList.add(record);
			}
		}

		long endTime = System.currentTimeMillis();
		double timeTaken = ((double) (endTime - beginTime)) / 1000;
		readerLogger.warn(String.format(
				"BaseDataReader: End reading BaseData (%s seconds)",
				new DecimalFormat("0.00").format(timeTaken)));

		beginTime = System.currentTimeMillis();
		readerLogger.warn("BaseDataReader: Begin persisting BaseData");

		if (dataList.size() > 0) {
			service.getPersistenceService().persistBaseDataCollection(dataList);
		}

		if (recordList.size() > 0) {
			service.getPersistenceService().persistErroneusRecordCollection(
					recordList);
		}

		endTime = System.currentTimeMillis();
		timeTaken = ((double) (endTime - beginTime)) / 1000;
		readerLogger.warn(String.format(
				"BaseDataReader: End reading BaseData (%s seconds)",
				new DecimalFormat("0.00").format(timeTaken)));

		currentRow = FIRSTROW;
	}

	/**
	 * Creates a Record object from a line of the excel sheet
	 * @param sheet
	 * @return Record valued with the excel content
	 */
	public Record read(HSSFSheet sheet) {

		Record record = new Record();
		HSSFRow row = sheet.getRow(currentRow);
		record.setDate(getDateFromCellAndSetDesc(row.getCell(0), record));
		record.setEventId(getIntegerFromCellAndSetDesc(row.getCell(1), record));
		record.setFailureType(getIntegerFromCellAndSetDesc(row.getCell(2),
				record));
		record.setUserEquipment(getIntegerFromCellAndSetDesc(row.getCell(3),
				record));
		record.setMcc(getIntegerFromCellAndSetDesc(row.getCell(4), record));
		record.setMnc(getIntegerFromCellAndSetDesc(row.getCell(5), record));
		record.setCellId(getIntegerFromCellAndSetDesc(row.getCell(6), record));
		record.setDuration(getIntegerFromCellAndSetDesc(row.getCell(7), record));
		record.setCauseCode(getIntegerFromCellAndSetDesc(row.getCell(8), record));
		record.setNeVersion(getStringFromCellAndSetDesc(row.getCell(9), record));
		record.setImsi(getBigIntFromCellAndSetDesc(row.getCell(10), record));
		record.setHier3Id(getBigIntFromCellAndSetDesc(row.getCell(11), record));
		record.setHier32Id(getBigIntFromCellAndSetDesc(row.getCell(12), record));
		record.setHier321Id(getBigIntFromCellAndSetDesc(row.getCell(13), record));

		currentRow++;

		// record.setId(currentRow++);

		return record;
	}

	/**
	 * If the cell doesn't contain an Integer, a description of the error 
	 * is set into the description property of the Record object.
	 * @param cell
	 * @param record
	 * @return Integer or null value if the cell doesn't contain a integer value
	 */
	public Integer getIntegerFromCellAndSetDesc(HSSFCell cell,
			IDescription record) {
		Integer integer = getIntegerFromCell(cell);
		if (integer == null) {
			record.setDescription("Cell Integer type at row " + currentRow
					+ " corrupted");
		}
		return integer;
	}

	/**
	 * If the cell doesn't contain a Date, a description of the error 
     * is set into the description property of the Record object.
     * @param cell
     * @param record
     * @return Date or null value if the cell doesn't contain a Date value
     */
	public Date getDateFromCellAndSetDesc(HSSFCell cell, IDescription record) {
		Date date = getDateFromCell(cell);
		if (date == null) {
			record.setDescription("Cell Date type at row " + currentRow
					+ " corrupted");
		}
		return date;
	}

    /**
     * If the cell doesn't contain a String, a description of the error 
     * is set into the description property of the Record object.
     * @param cell
     * @param record
     * @return String or null value if the cell doesn't contain a String value
     */
	public String getStringFromCellAndSetDesc(HSSFCell cell, IDescription record) {
		String string = getStringFromCell(cell);
		if (string == null) {
			record.setDescription("Cell String type at row " + currentRow
					+ " corrupted");
		}
		return string;
	}

    /**
     * If the cell doesn't contain a BigInteger, a description of the error 
     * is set into the description property of the Record object.
     * @param cell
     * @param record
     * @return BigInteger or null value if the cell doesn't contain a BigInteger value
     */
	public BigInteger getBigIntFromCellAndSetDesc(HSSFCell cell,
			IDescription record) {
		BigInteger bigInt = getBigIntFromCell(cell);
		if (bigInt == null) {
			record.setDescription("Cell Big Integer type at row " + currentRow
					+ " corrupted");
		}
		return bigInt;
	}

	/**
	 * Creates a AllTrueCompositeValidator object and adds all the required
	 * IValidator objects: DateValidator, EventCauseValidator, FailureTypeValidator,
	 * OperatorCountryValidator, UserEquipmentValidator
	 * @return
	 */
	public static IValidator createValidator() {
		AllTrueCompositeValidator validator = new AllTrueCompositeValidator();
		validator.add(new DateValidator());
		validator.add(new EventCauseValidator());
		validator.add(new FailureTypeValidator());
		validator.add(new OperatorCountryValidator());
		validator.add(new UserEquipmentValidator());
		return validator;
	}

	/**
	 * returns the name of the excel sheet
	 * @return
	 */
	public static String getName() {
		return NAME;
	}

}
