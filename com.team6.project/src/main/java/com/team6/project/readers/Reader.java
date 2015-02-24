
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

    public Reader() {
        currentRow = FIRSTROW;
    }

    public abstract void processExcelFile(DataImportServiceLocal service);

    public Integer getIntegerFromCell(HSSFCell cell) {
        if (!(cell == null) && !(cell.getCellType() == Cell.CELL_TYPE_BLANK)) {
            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                return (int) cell.getNumericCellValue();
            }
        }
        return null;
    }

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

    public String getStringFromCell(HSSFCell cell) {
        if (!(cell == null) && !(cell.getCellType() == Cell.CELL_TYPE_BLANK)) {
            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                return cell.getStringCellValue();
            }
        }
        return null;
    }

    public BigInteger getBigIntFromCell(HSSFCell cell) {
        if (!(cell == null) && !(cell.getCellType() == Cell.CELL_TYPE_BLANK)) {
            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                return new BigInteger(NumberToTextConverter.toText(cell
                        .getNumericCellValue()));
            }
        }
        return null;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public static int getFIRSTROW() {
        return FIRSTROW;
    }

    public static void setFIRSTROW(int fIRSTROW) {
        FIRSTROW = fIRSTROW;
    }
    
    
    
}
