package com.LeadForm.utility;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ReadExcelFile {
    public static FileInputStream inputStream;
    public static XSSFWorkbook workBook;
    public static XSSFSheet excelSheet;
    public static XSSFRow row;
    public static XSSFCell cell;

    // ✅ Updated method to correctly handle numeric values
    public static String getCellValue(String fileName, String sheetName, int rowNo, int cellNo) {
        String cellValue = "";
        try {
            inputStream = new FileInputStream(fileName);
            workBook = new XSSFWorkbook(inputStream);
            excelSheet = workBook.getSheet(sheetName);
            
            if (excelSheet == null) {
                System.out.println("Sheet not found: " + sheetName);
                return "";
            }

            row = excelSheet.getRow(rowNo);
            if (row == null) {
                System.out.println("Row " + rowNo + " is empty");
                return "";
            }

            cell = row.getCell(cellNo);
            if (cell == null) {
                System.out.println("Cell at Row " + rowNo + " Column " + cellNo + " is empty");
                return "";
            }

            // ✅ Handling different cell types
            switch (cell.getCellType()) {
                case STRING:
                    cellValue = cell.getStringCellValue().trim();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        cellValue = dateFormat.format(cell.getDateCellValue()); // Convert date to string
                    } else {
                        cellValue = String.valueOf((long) cell.getNumericCellValue()); // Convert number to string
                    }
                    break;
                case BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case FORMULA:
                    cellValue = cell.getCellFormula();
                    break;
                case BLANK:
                    cellValue = "";
                    break;
                default:
                    cellValue = "";
            }

            workBook.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cellValue;
    }

    public static int getRowCount(String fileName, String sheetName) {
        try {
            inputStream = new FileInputStream(fileName);
            workBook = new XSSFWorkbook(inputStream);
            excelSheet = workBook.getSheet(sheetName);

            int ttlRows = excelSheet.getLastRowNum() + 1;
            workBook.close();
            return ttlRows;
        } catch (Exception e) {
            return 0;
        }
    }

    public static int getColCount(String fileName, String sheetName) {
        try {
            inputStream = new FileInputStream(fileName);
            workBook = new XSSFWorkbook(inputStream);
            excelSheet = workBook.getSheet(sheetName);

            int ttlCells = excelSheet.getRow(0).getLastCellNum();
            workBook.close();
            return ttlCells;
        } catch (Exception e) {
            return 0;
        }
    }

    // ✅ Updated to handle String values properly
    public String getStringData(String sheetName, int row, int column) {
        try {
            return getCellValue(sheetName, sheetName, row, column);
        } catch (Exception e) {
            return "";
        }
    }

    // ✅ Updated method to handle numeric values
    public String getNumericData(String sheetName, int row, int column) {
        try {
            return getCellValue(sheetName, sheetName, row, column);
        } catch (Exception e) {
            return "";
        }
    }
}
