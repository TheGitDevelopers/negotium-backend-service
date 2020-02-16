package com.negotium.automatic.driver;

public enum BrowserType {

    CHROME("chrome"),
    FIREFOX("firefox"),
    IE("internetexplorer");

    private final String browser;

    BrowserType(String browser) {
        this.browser = browser;
    }
}