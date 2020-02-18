package com.negotium.automatic.driver.manager;

import com.negotium.automatic.driver.BrowserFactory;
import com.negotium.automatic.driver.BrowserType;
import org.openqa.selenium.WebDriver;

import static com.negotium.automatic.configuration.TestRunProperties.getBrowserToRun;
import static com.negotium.automatic.configuration.TestRunProperties.getIsRemoteRun;
import static com.negotium.automatic.driver.BrowserType.FIREFOX;

public class DriverManager {

    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal();
    private static ThreadLocal<BrowserType> browserTypeThreadLocal = new ThreadLocal<>();

    private DriverManager() {
    }


    public static void setWebDriver(BrowserType browserType){
        WebDriver webDriver = null;

        if(browserType == null){
            browserType = getBrowserToRun();
            webDriver = new BrowserFactory(browserType, getIsRemoteRun()).getBrowser();
        }else {
            webDriver = new BrowserFactory(browserType, getIsRemoteRun()).getBrowser();
        }
        browserTypeThreadLocal.set(browserType);
        webDriverThreadLocal.set(webDriver);
    }

    public static WebDriver getWebDriver() {
        if (webDriverThreadLocal == null) {
            throw new IllegalStateException("WebDriver Instance was null! Please create instance of WebDriver using setWebDriver");
        }
        return webDriverThreadLocal.get();
    }

    public static void disposeDriver() {
        webDriverThreadLocal.get().close();
        if (!browserTypeThreadLocal.get().equals(FIREFOX)){
            webDriverThreadLocal.get().quit();
        }
        webDriverThreadLocal.remove();
        browserTypeThreadLocal.remove();
    }
}