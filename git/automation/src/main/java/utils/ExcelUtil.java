package utils;

import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;

public class ExcelUtil {

    public static String getCellData(String filePath, String sheetName, int rowNum, int colNum) {
        try {
            FileInputStream fis = new FileInputStream(new File(filePath));
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(colNum);
            fis.close();

            return cell.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
