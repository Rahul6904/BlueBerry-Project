package com.LeadForm.testcases;

import org.testng.annotations.Test;

import com.LeadForm.pages.BaseTest;
import com.LeadForm.pages.LoginPage;


public class LoginTest extends BaseTest {
	
	@Test
	void verifyLogin(String usreEmail, String userPwd) 
	{
		LoginPage p=new LoginPage(driver);
		String username="12240009";
		String password="123456789";
		p.loginportal(username, password);
	}
	
}
