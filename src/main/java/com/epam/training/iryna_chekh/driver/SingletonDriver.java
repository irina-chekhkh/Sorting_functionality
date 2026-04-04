package com.epam.training.iryna_chekh.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SingletonDriver {
    private static final Logger LOGGER = Logger.getLogger(SingletonDriver.class.getName());
    private static volatile SingletonDriver instance;
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private SingletonDriver() {
    }

    public static SingletonDriver getInstance(String browser) {
        LOGGER.log(Level.INFO, "Check if instance is null");
        if (instance == null) {
            synchronized (SingletonDriver.class) {
                LOGGER.log(Level.INFO, "Instance is null, init instance");
                instance = new SingletonDriver();
            }
        }
        LOGGER.log(Level.INFO, "Check if driver is null");
        if (driver.get() == null) {
            LOGGER.log(Level.INFO, "Driver not initialized, creating new one");
            initDriver(browser);
        }
        return instance;
    }

    private static void initDriver(String browser) {
        LOGGER.log(Level.WARNING, "Initializing driver for browser: {0}", browser);
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                LOGGER.log(Level.INFO, "Browser is Chrome, setup complete");
                driver.set(new ChromeDriver());
                LOGGER.log(Level.INFO, "Driver set for Chrome");
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                LOGGER.log(Level.INFO, "Browser is Firefox, setup complete");
                driver.set(new FirefoxDriver());
                LOGGER.log(Level.INFO, "Driver set for Firefox");
                break;
            default:
                LOGGER.log(Level.SEVERE, "Unknown browser: {0}", browser);
                throw new IllegalArgumentException("Can't find browser: " + browser);
        }
    }

    public static WebDriver getDriver() {
        LOGGER.log(Level.INFO, "Get driver from SingletonDriver");
        return driver.get();
    }

    public static void closeDriver() {
        LOGGER.log(Level.INFO, "Check if driver is null before closing");
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
            LOGGER.log(Level.INFO, "Driver closed and removed");
        }else {
            LOGGER.log(Level.WARNING, "Driver was already null, nothing to close");
        }
    }

    @Override
    public String toString() {
        LOGGER.log(Level.INFO, "Check driver is null in toString()");
        if (driver.get() != null) {
            Capabilities caps = ((HasCapabilities) driver).getCapabilities();
            LOGGER.log(Level.INFO, "Get browser name");
            LOGGER.log(Level.INFO, "Get browser name from capabilities");
            return caps.getBrowserName();
        }
        LOGGER.log(Level.WARNING, "Driver is null, returning null browser name");
        return null;
    }


}
