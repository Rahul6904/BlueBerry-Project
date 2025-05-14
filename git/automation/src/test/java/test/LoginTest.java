package test;

import org.testng.annotations.Test;
import utils.ExcelUtil;

public class LoginTest {

    @Test
    public void loginWithExcelData() {
        String filePath = "excel-data/B2E.xlsx";
        String sheetName = "Sheet1";

        String username = ExcelUtil.getCellData(filePath, sheetName, 1, 0);
        String password = ExcelUtil.getCellData(filePath, sheetName, 1, 1);

        System.out.println("Username from Excel: " + username);
        System.out.println("Password from Excel: " + password);

        // here you would normally pass these into your login method
    }
}
