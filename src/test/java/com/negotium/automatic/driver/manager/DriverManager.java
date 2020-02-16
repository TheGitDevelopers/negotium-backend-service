package com.negotium.automatic.driver.manager;

import com.negotium.automatic.configuration.LocalWebDriverProperties;
import com.negotium.automatic.driver.BrowserFactory;
import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static WebDriver driver;

    private DriverManager() {
    }

    public static WebDriver getWebDriver() {
        if (driver != null) {
            driver = BrowserFactory.getBrowser(LocalWebDriverProperties.getLocalBrowser());
        }
        return driver;
    }

    public static void disposeDriver() {
        driver.close();
        if (!LocalWebDriverProperties.getLocalBrowser().equals(LocalWebDriverProperties.getFirefoxWebDriverLocation())){
            driver.quit();
        }
        driver = null;
    }
}