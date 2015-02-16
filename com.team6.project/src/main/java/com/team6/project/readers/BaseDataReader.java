package com.team6.project.readers;

import java.math.BigInteger;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;

import com.team6.project.entities.BaseData;
import com.team6.project.entities.IDescription;
import com.team6.project.entities.Record;
import com.team6.project.services.MapExcelInterface;
import com.team6.project.validators.AllTrueCompositeValidator;
import com.team6.project.validators.DateValidator;
import com.team6.project.validators.EventCauseValidator;
import com.team6.project.validators.FailureTypeValidator;
import com.team6.project.validators.IValidator;
import com.team6.project.validators.OperatorCountryValidator;
import com.team6.project.validators.UserEquipmentValidator;

/**
 * Reads rows in sheet called UE Table. Create the UserEquipment object. If the
 * object is not already in the appropriated map it is added and written to the
 * DB
 * 
 * @author Cristiana
 */
public class BaseDataReader extends Reader {

    private static final String NAME = "Base Data";

    @Override
    public void processExcelFile(MapExcelInterface service) {
        // List<Map> maps = service.getMaps();
        HSSFSheet sheet = service.getSheet(NAME);
        IValidator validator = createValidator();
        while (currentRow <= sheet.getLastRowNum()) {
            Record record = read(sheet);
            BaseData baseData = new BaseData();
            boolean isValid = validator.isValid(record, baseData, service);
            if (isValid) {
                // em.persist(baseData);
            } else {
                // em.persist(record);
            }
        }

    }

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

        return record;

    }

    public Integer getIntegerFromCellAndSetDesc(HSSFCell cell,
            IDescription record) {
        Integer integer = getIntegerFromCell(cell);
        if (integer == null) {
            record.setDescription("Cell " + cell.getColumnIndex()
                    + " wrong cell type, was " + cell.getCellType());
        }
        return integer;
    }

    
    public Date getDateFromCellAndSetDesc(HSSFCell cell, IDescription record) {
        Date date = getDateFromCell(cell);
        if (date == null) {
            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                record.setDescription("Cell " + cell.getColumnIndex()
                        + " is not date formatted");
            } else {
                record.setDescription("Cell " + cell.getColumnIndex()
                        + " wrong cell type, is " + cell.getCellType());
            }

        }
        return date;
    }

    
    public String getStringFromCellAndSetDesc(HSSFCell cell, IDescription record) {
        String string = getStringFromCell(cell);
        if (string == null) {
            record.setDescription("Cell " + cell.getColumnIndex()
                    + " wrong cell type, was " + cell.getCellType());
        }
        return string;
    }

    
    public BigInteger getBigIntFromCellAndSetDesc(HSSFCell cell,
            IDescription record) {
        BigInteger bigInt = getBigIntFromCell(cell);
        if (bigInt == null) {
            record.setDescription("Cell " + cell.getColumnIndex()
                    + " wrong cell type, was " + cell.getCellType());
        }
        return bigInt;
    }

    public static IValidator createValidator() {
        AllTrueCompositeValidator validator = new AllTrueCompositeValidator();
        validator.add(new DateValidator());
        validator.add(new EventCauseValidator());
        validator.add(new FailureTypeValidator());
        validator.add(new OperatorCountryValidator());
        validator.add(new UserEquipmentValidator());
        return validator;
    }

}
