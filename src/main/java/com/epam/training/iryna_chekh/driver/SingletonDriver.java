package com.epam.training.iryna_chekh.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SingletonDriver {
    private static volatile SingletonDriver instance;
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private SingletonDriver() {
    }

    public static SingletonDriver getInstance(String browser) {
        if (instance == null) {
            synchronized (SingletonDriver.class) {
                instance = new SingletonDriver();
            }
        }
        if (driver.get() == null) {
            initDriver(browser);
        }
        return instance;
    }

    private static void initDriver(String browser) {
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;
            default:
                throw new IllegalArgumentException("Can't find browser: " + browser);
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void closeDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    @Override
    public String toString() {
        if (driver != null) {
            Capabilities caps = ((HasCapabilities) driver).getCapabilities();
            return caps.getBrowserName();
        }
        return null;
    }


}
