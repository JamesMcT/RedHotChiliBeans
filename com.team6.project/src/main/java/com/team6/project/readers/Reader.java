
package com.team6.project.readers;

import java.math.BigInteger;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.NumberToTextConverter;

import com.team6.project.services.DataImportServiceLocal;

/**
 * Abstract class Reader defines the generic behavior of each specific reader.
 * It implements basic methods used to retrieve a specific Type from an excel
 * cell (Numeric Cell, String Cell, Date Cell)
 * 
 * @author Cristiana
 */
public abstract class Reader {

    protected static org.apache.log4j.Logger readerLogger = org.apache.log4j.Logger
            .getLogger(Reader.class);
    protected int currentRow;
    protected static int FIRSTROW = 1;

    /**
     * Initializes the current row as FIRSTROW
     */
    public Reader() {
        currentRow = FIRSTROW;
    }

    /**
     * Process the excel file reading the appropiated sheet and filling the related map
     * of the DataImportServiceLocal object
     * @param DataImportServiceLocal object contains the HSSFWorkbook and the maps with the
     * data already stored in the DB
     */
    public abstract void processExcelFile(DataImportServiceLocal service);

    /**
     * Checks that the value in the cell is an integer
     * @param cell of the excel sheet
     * @return the integer contained in the cell or a null value if the cell doesn't contain an integer
     */
    public Integer getIntegerFromCell(HSSFCell cell) {
        if (!(cell == null) && !(cell.getCellType() == Cell.CELL_TYPE_BLANK)) {
            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                return (int) cell.getNumericCellValue();
            }
        }
        return null;
    }

    /**
     * Checks that the value in the cell is a date
     * @param cell of the excel sheet
     * @return the date contained in the cell or a null value if the cell doesn't contain a date
     */
    public Date getDateFromCell(HSSFCell cell) {
        if (!(cell == null) && !(cell.getCellType() == Cell.CELL_TYPE_BLANK)) {
            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                }
                return null;
            }
        }
        return null;
    }

    /**
     * Checks that the value in the cell is a String
     * @param cell of the excel sheet
     * @return String contained in the cell or a null value if the cell doesn't contain a String
     */
    public String getStringFromCell(HSSFCell cell) {
        if (!(cell == null) && !(cell.getCellType() == Cell.CELL_TYPE_BLANK)) {
            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                return cell.getStringCellValue();
            }
        }
        return null;
    }

    /**
     * Checks that the value in the cell is a BigInteger
     * @param cell of the excel sheet
     * @return BigInteger contained in the cell or a null value if the cell doesn't contain a BigInteger
     */
    public BigInteger getBigIntFromCell(HSSFCell cell) {
        if (!(cell == null) && !(cell.getCellType() == Cell.CELL_TYPE_BLANK)) {
            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                return new BigInteger(NumberToTextConverter.toText(cell
                        .getNumericCellValue()));
            }
        }
        return null;
    }

    /**
     * get the value of the currentRow
     * @return
     */
    public int getCurrentRow() {
        return currentRow;
    }

    /**
     * set the value of the currentRow
     * @param currentRow
     */
    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

/*    *//**
     * get the value of the FIRSTROW
     * @return
     *//*
    public static int getFIRSTROW() {
        return FIRSTROW;
    }

    *//**
     * 
     * @param fIRSTROW
     *//*
    public static void setFIRSTROW(int fIRSTROW) {
        FIRSTROW = fIRSTROW;
    }*/
    
    
    
}
