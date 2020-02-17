package com.negotium.automatic.driver.manager;

import com.negotium.automatic.driver.BrowserFactory;
import org.openqa.selenium.WebDriver;

import static com.negotium.automatic.configuration.TestRunProperties.getBrowserToRun;
import static com.negotium.automatic.configuration.TestRunProperties.getIsRemoteRun;
import static com.negotium.automatic.driver.BrowserType.FIREFOX;

public class DriverManager {

    private static WebDriver driver;

    private DriverManager() {
    }

    public static WebDriver getWebDriver() {
        if (driver != null) {
            driver = new BrowserFactory(getBrowserToRun(), getIsRemoteRun()).getBrowser();
        }
        return driver;
    }

    public static void disposeDriver() {
        driver.close();
        if (!getBrowserToRun().equals(FIREFOX)){
            driver.quit();
        }
        driver = null;
    }
}