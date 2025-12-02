package com.jaqueline.qa.base;

import com.jaqueline.qa.utils.ConfigReader;
import com.jaqueline.qa.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    @BeforeMethod
    public void setup() {
        String browser = ConfigReader.getProperty("browser");

        DriverManager.initDriver(browser);

        DriverManager.getDriver().get(ConfigReader.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() {

        DriverManager.quitDriver();
    }
}
