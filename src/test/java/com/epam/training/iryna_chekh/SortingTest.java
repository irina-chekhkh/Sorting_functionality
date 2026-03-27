package com.epam.training.iryna_chekh;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SortingTest {
    private WebDriver driver;
    private final String resourcesPath = "src\\main\\resources\\";

    @Parameters("browser")
    @BeforeMethod
    public void initDriver(String browser) {
        if (driver == null) {
            switch (browser) {
                case "chrome":
                    System.setProperty("webbrowser.chrome.driver", resourcesPath + "chromedriver.exe");
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    System.setProperty("webbrowser.firefox.driver", resourcesPath + "geckodriver.exe");
                    driver = new FirefoxDriver();
                    break;
            }
        }
        driver.manage().window().maximize();
    }

    @Test
    public void openPage() {
        driver.get("https://www.saucedemo.com/");
        int i = 0;
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
        driver=null;
    }
}
