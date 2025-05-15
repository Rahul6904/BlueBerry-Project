package com.LeadForm.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.LeadForm.pages.BaseTest;
import com.LeadForm.pages.LoginPage;
import com.LeadForm.utility.ReadExcelFile;

public class LoginTestDriverData extends BaseTest {
	String fileName=System.getProperty("user.dir")+"\\TestData\\LoginPageTestcaseexcel.xlsx";
	@Test(priority =1,dataProvider="LoginDataProvider")
	void verifyLogin(String userEmail, String userPwd) 
	{
	    System.out.println("Testing Login with:");
	    System.out.println("Username: " + userEmail);
	    System.out.println("Password: " + userPwd);

	    LoginPage p = new LoginPage(driver);
	    p.loginportal(userEmail, userPwd);
//	    p.logout();
	}
	
	@DataProvider(name="LoginDataProvider")
	public String[][] LoginDataProvider()
	{
	    int ttlRows = ReadExcelFile.getRowCount(fileName, "LoginData");
	    int ttlColumns = ReadExcelFile.getColCount(fileName, "LoginData");

	    System.out.println("Total Rows: " + ttlRows);
	    System.out.println("Total Columns: " + ttlColumns);

	    if (ttlRows <= 0 || ttlColumns <= 0) {
	        throw new RuntimeException("Excel sheet is empty or not formatted correctly!");
	    }

	    String data[][] = new String[ttlRows - 1][ttlColumns];

	    for (int i = 1; i < ttlRows; i++) {
	        for (int j = 0; j < ttlColumns; j++) {
	            String cellValue = ReadExcelFile.getCellValue(fileName, "LoginData", i, j);
	            data[i - 1][j] = (cellValue != null) ? cellValue : "";
	        }
	    }
	    return data;
	}
}