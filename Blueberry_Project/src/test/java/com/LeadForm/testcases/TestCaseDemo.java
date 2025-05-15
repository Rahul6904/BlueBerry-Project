package com.LeadForm.testcases;

import org.testng.annotations.Test;
import com.LeadForm.pages.BaseTest;

public class TestCaseDemo extends BaseTest {
    @Test
    public void testcheck() {
        System.out.println("Test execution started...");
        driver.get(config.getStagingUrl());
        System.out.println("Page title: " + driver.getTitle());
    }
}
