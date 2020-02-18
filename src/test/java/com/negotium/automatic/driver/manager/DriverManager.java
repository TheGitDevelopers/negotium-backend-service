package com.negotium.automatic.driver.manager;

import com.negotium.automatic.driver.BrowserFactory;
import org.openqa.selenium.WebDriver;

import static com.negotium.automatic.configuration.TestRunProperties.getBrowserToRun;
import static com.negotium.automatic.configuration.TestRunProperties.getIsRemoteRun;
import static com.negotium.automatic.driver.BrowserType.FIREFOX;

public class DriverManager {

    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal();

    private DriverManager() {
    }

    public static WebDriver getWebDriver() {
        if (webDriverThreadLocal == null) {
            webDriverThreadLocal.set(new BrowserFactory(getBrowserToRun(), getIsRemoteRun()).getBrowser());
        }
        return webDriverThreadLocal.get();
    }

    public static void disposeDriver() {
        webDriverThreadLocal.get().close();
        if (!getBrowserToRun().equals(FIREFOX)){
            webDriverThreadLocal.get().quit();
        }
        webDriverThreadLocal.remove();
    }
}