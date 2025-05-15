package com.LeadForm.pages;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.LeadForm.utility.ConfigDataProvider;
import com.LeadForm.utility.BrowserFectory;

public class BaseTest {
    public WebDriver driver;
    public ConfigDataProvider config;

    @BeforeClass
    public void setUp() {
        try {
            config = new ConfigDataProvider();
            driver = BrowserFectory.startApplication(config.getBrowser(), config.getStagingUrl());
            if (driver == null) {
                throw new RuntimeException("WebDriver initialization failed.");
            }
        } catch (Exception e) {
            System.out.println("Error in BaseTest setup: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        BrowserFectory.quitBrowser(driver);
    }
}
