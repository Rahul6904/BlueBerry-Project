package com.LeadForm.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	WebDriver driver;
	//constructor
	public LoginPage(WebDriver lDriver)
	{
		this.driver=lDriver;
		
		PageFactory.initElements(driver,this);
	}
	
	
    @FindBy(xpath="//input[@placeholder='Email or phone']") WebElement uname;
	
	@FindBy(xpath="//input[@placeholder='Enter your password']") WebElement pass;
	
	@FindBy(xpath="//div[@class='loginIn']") WebElement loginButton;
	
//	@FindBy(xpath="//span[normalize-space()='Logout']") WebElement profile;
//	@FindBy(xpath="//button[normalize-space()='Log out']") WebElement logout;
	
	public void loginportal(String username, String password) 
	{
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	    wait.until(ExpectedConditions.visibilityOf(uname)).clear();
	    uname.sendKeys(username);

	    wait.until(ExpectedConditions.visibilityOf(pass)).click();
	    pass.clear();
	    pass.sendKeys(password);

	    wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
	}
//	public void logout() 
//	{
//		logout.click();
//		
//	}
}
