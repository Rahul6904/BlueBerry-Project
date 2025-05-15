package com.LeadForm.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.LeadForm.pages.BaseTest;
import com.LeadForm.pages.LeadFormPageB2C;
import com.LeadForm.utility.ReadExcelFile;

public class LeadFormTestCases extends BaseTest {
    String fileName = System.getProperty("user.dir") + "\\TestData\\LoginPageTestcaseexcel.xlsx";

    @Test(priority = 1, dataProvider = "LeadFormTestCases")
    void leadFormTest(String Username, String Password, String name, String contactName, 
                      String code, String number, String email, String filePath, 
                      String date, String time, String source, String type, 
                      String address, String description) {
        LeadFormPageB2C p = new LeadFormPageB2C(driver);
        System.out.println("🔹 Logging in with: " + Username + " / " + Password);

        try {
            p.loginportal(Username, Password);
            Thread.sleep(2000);  // Give time for the dashboard to load

            System.out.println("✅ Successfully logged in. Navigating to Lead Form...");
            p.navigateToLeadForm();
            Thread.sleep(2000);

            System.out.println("📝 Entering Lead Form Details...");
            p.enterClientDetails(name, contactName, code, number, email, filePath, date, time, source, type, address, description);
            System.out.println("✅ Lead Form submitted successfully!");
        } catch (Exception e) {
            System.err.println("❌ Error filling Lead Form: " + e.getMessage());
        }
    }

    @DataProvider(name = "LeadFormTestCases")
    public Object[][] LoginDataProvider() {
        int loginRows = ReadExcelFile.getRowCount(fileName, "LoginData");
        int loginCols = ReadExcelFile.getColCount(fileName, "LoginData");
        int leadFormRows = ReadExcelFile.getRowCount(fileName, "LeadForm");
        int leadFormCols = ReadExcelFile.getColCount(fileName, "LeadForm");

        int totalRows = Math.min(loginRows, leadFormRows) - 1;  // Ensure row count match
        Object[][] data = new Object[totalRows][loginCols + leadFormCols];

        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < loginCols; j++) {
                data[i - 1][j] = ReadExcelFile.getCellValue(fileName, "LoginData", i, j);
            }
            for (int j = 0; j < leadFormCols; j++) {
                data[i - 1][loginCols + j] = ReadExcelFile.getCellValue(fileName, "LeadForm", i, j);
            }
        }
        return data;
    }
}
