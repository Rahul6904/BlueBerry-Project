package com.LeadForm.pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeadFormPageB2C {
    WebDriver driver;
    WebDriverWait wait;

    public LeadFormPageB2C(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Login Elements
    @FindBy(xpath = "//input[@placeholder='Email or phone']")
    WebElement Email_PhoneNumber;

    @FindBy(xpath = "//input[@placeholder='Enter your password']")
    WebElement Password;

    @FindBy(xpath = "//button[.//span[normalize-space()='Login']]")
    WebElement Login;

    // Navigation Elements
    @FindBy(xpath = "//i[@class='mat-mdc-tooltip-trigger addicon fa-users fas']")
    WebElement CRMModule;

    @FindBy(xpath = "//li[@class='ng-star-inserted']//li[2]//span[1]")
    WebElement LeadSystemmainModule;

    @FindBy(xpath = "//span[normalize-space()='Create Lead']")
    WebElement CreateLeadModule;

    // Lead Form Elements
    @FindBy(xpath = "(//input[@id='mat-input-7'])[1]")
    WebElement clientName;

    @FindBy(xpath = "(//input[@id='mat-input-1'])[1]")
    WebElement contactPersonName;

    @FindBy(xpath = "//mat-label[text()='C Code']")  
    WebElement C_Code;  

    @FindBy(xpath = "(//input[@id='mat-input-2'])[1]")
    WebElement contactNumber;

    @FindBy(xpath = "(//input[@id='mat-input-3'])[1]")
    WebElement mailId;

    @FindBy(xpath = "//input[@title='Termination Copy Upload']")
    WebElement chooseFile;

    @FindBy(xpath = "(//button[@aria-label='Open calendar'])[2]")
    WebElement FollowUpDate;

    @FindBy(xpath = "//input[@id='followUpTime']")
    WebElement FollowUpTime;

    @FindBy(xpath = "//mat-select[@id='mat-select-4']")
    WebElement ContactSource;

    @FindBy(xpath = "//mat-select[@id='mat-select-7']")
    WebElement Status;

    @FindBy(xpath = "//textarea[@type='text']")
    WebElement Address;

    @FindBy(xpath = "//iframe[@title='Editor, editor2']")
    WebElement CommentFrame;

    @FindBy(xpath = "//body")
    WebElement Comment;

    @FindBy(xpath = "//span[text()=' Save ']/parent::button")
    WebElement SaveBtn;

    // ================== LOGIN METHOD ==================
    public void loginportal(String userEmail, String userPwd) {
        try {
            wait.until(ExpectedConditions.visibilityOf(Email_PhoneNumber)).clear();
            Email_PhoneNumber.sendKeys(userEmail);

            wait.until(ExpectedConditions.visibilityOf(Password)).clear();
            Password.sendKeys(userPwd);

            wait.until(ExpectedConditions.elementToBeClickable(Login)).click();
        } catch (Exception e) {
            System.err.println("Error during login: " + e.getMessage());
        }
    }

    // ================== NAVIGATION METHOD ==================
    public void navigateToLeadForm() {
        try {
            scrollAndClick(CRMModule);
            Thread.sleep(1000);
            scrollAndClick(LeadSystemmainModule);
            Thread.sleep(1000);
            scrollAndClick(CreateLeadModule);
        } catch (Exception e) {
            System.err.println("Error navigating to Lead Form: " + e.getMessage());
        }
    }

    // ================== ENTER LEAD FORM DETAILS ==================
    public void enterClientDetails(String name, String contactName, String code,
                                   String number, String email, String filePath, String date,
                                   String time, String source, String type, String add,
                                   String description) {
        try {
            wait.until(ExpectedConditions.visibilityOf(clientName)).sendKeys(name);
            wait.until(ExpectedConditions.visibilityOf(contactPersonName)).sendKeys(contactName);

            // Select Country Code
            wait.until(ExpectedConditions.elementToBeClickable(C_Code)).click();
            WebElement countryOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//mat-option//span[contains(text(), '" + code + "')]")
            ));
            countryOption.click();

            wait.until(ExpectedConditions.visibilityOf(contactNumber)).sendKeys(number);
            wait.until(ExpectedConditions.visibilityOf(mailId)).sendKeys(email);

            // File Upload
            if (!filePath.equals("C:\\Users\\Mindz123\\Downloads\\Scope Document for a Food Delivery Application.pdf")) {
                chooseFile.sendKeys(filePath);
            }

            // Date & Time Input
            if (!date.equals("20/03/2025")) {
                FollowUpDate.sendKeys(date);
            }
            if (!time.equals("4:49 PM")) {
                FollowUpTime.sendKeys(time);
            }

            // Dropdown Selections
            selectDropdown(ContactSource, source);
            selectDropdown(Status, type);

            // Enter Address
            wait.until(ExpectedConditions.visibilityOf(Address)).sendKeys(add);

            // Enter Comment inside iframe
            driver.switchTo().frame(CommentFrame);
            wait.until(ExpectedConditions.visibilityOf(Comment)).sendKeys(description);
            driver.switchTo().defaultContent();

            // Click Save Button
            wait.until(ExpectedConditions.elementToBeClickable(SaveBtn)).click();
        } catch (Exception e) {
            System.err.println("Error filling Lead Form: " + e.getMessage());
        }
    }
    
    // ================== HELPER METHODS ==================
    private void selectDropdown(WebElement dropdown, String optionText) {
        try {
            dropdown.click();
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//mat-option//span[contains(text(), '" + optionText + "')]"))
            );
            option.click();
        } catch (Exception e) {
            System.err.println("Dropdown selection failed: " + e.getMessage());
        }
    }

    private void scrollAndClick(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            System.err.println("Element not clickable: " + e.getMessage());
        }
    }
}
