package com.epam.training.iryna_chekh;

import com.epam.training.iryna_chekh.page.LoginPage;
import com.epam.training.iryna_chekh.page.ProductsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;


public class SortingTest {
    private WebDriver driver;
    private final String resourcesPath = "src\\main\\resources\\";
    private final User currentUser = new User();


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

    private ProductsPage login(User user) {
        return new LoginPage(driver)
                .openPage()
                .enterUserNameAndPassword(currentUser)
                .login();
    }

    @Test
    public void shouldSortTitlesAscending() {
        List<String> actualNames = login(currentUser)
                .sortElements(SortingParameter.TITLE_ASC)
                .getProductsNames();
        List<String> expectedNames = actualNames.stream().sorted().collect(Collectors.toList());

        assertEquals(actualNames, expectedNames);
    }

    @Test
    public void shouldSortTitlesDescending() {
        List<String> actualNames = login(currentUser)
                .sortElements(SortingParameter.TITLE_DES)
                .getProductsNames();
        List<String> expectedNames = actualNames.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        assertEquals(actualNames, expectedNames);
    }

    @Test
    public void shouldSortPricesAscending() {
        List<Double> actualPrices = login(currentUser)
                .sortElements(SortingParameter.PRICE_ASC)
                .getProductsPrice();
        List<Double> expectedPrices = actualPrices.stream().sorted().collect(Collectors.toList());
        assertEquals(actualPrices, expectedPrices);
    }

    @Test
    public void shouldSortPricesDescending() {
        List<Double> actualPrices = login(currentUser)
                .sortElements(SortingParameter.PRICE_DES)
                .getProductsPrice();
        List<Double> expectedPrices = actualPrices.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        assertEquals(actualPrices, expectedPrices);
    }


    @AfterMethod
    public void closeDriver() {
        driver.quit();
        driver = null;
    }
}
