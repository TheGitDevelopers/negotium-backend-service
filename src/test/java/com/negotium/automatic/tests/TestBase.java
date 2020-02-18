package com.negotium.automatic.tests;

import com.negotium.automatic.configuration.ConfigurationProperties;
import com.negotium.automatic.configuration.PropertiesLoader;
import com.negotium.automatic.driver.BrowserType;
import com.negotium.automatic.driver.manager.DriverManager;
import com.negotium.automatic.driver.manager.DriverUtils;
import org.testng.annotations.*;

import java.util.Properties;

import static com.negotium.automatic.navigation.ApplicationURLs.APPLICATION_URL;

public class TestBase {

    @BeforeClass
    public void beforeClass() {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        Properties propertiesFromFile = propertiesLoader.getPropertiesFromFile("configuration.properties");
        ConfigurationProperties.setProperties(propertiesFromFile);
    }

    @Parameters("browser")
    @BeforeMethod
    public void beforeTest(@Optional BrowserType browserType){
        DriverManager.setWebDriver(browserType);
        DriverManager.getWebDriver();
        DriverUtils.setInitialConfiguration();
        DriverUtils.navigateToPage(APPLICATION_URL);
    }

    @AfterMethod
    public void afterTest(){
        DriverManager.disposeDriver();
    }
}