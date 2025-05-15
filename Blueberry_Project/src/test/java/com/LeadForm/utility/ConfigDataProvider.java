package com.LeadForm.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {
    Properties pro;

    public ConfigDataProvider() {
        try {
            File src = new File("./Configuration/config.properties");
            if (!src.exists()) {
                throw new RuntimeException("Config file not found: " + src.getAbsolutePath());
            }

            FileInputStream fis = new FileInputStream(src);
            pro = new Properties();
            pro.load(fis);
        } catch (Exception e) {
            System.out.println("Not able to load config file: " + e.getMessage());
        }
    }

    public String getBrowser() {
        return pro.getProperty("browser", "Chrome");
    }

    public String getStagingUrl() {
        return pro.getProperty("testurl", "https://default.url.com");
    }
}
