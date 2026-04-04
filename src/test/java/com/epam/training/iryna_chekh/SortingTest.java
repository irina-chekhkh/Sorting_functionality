package com.epam.training.iryna_chekh;

import com.epam.training.iryna_chekh.driver.SingletonDriver;
import com.epam.training.iryna_chekh.page.LoginPage;
import com.epam.training.iryna_chekh.page.ProductsPage;
import com.epam.training.iryna_chekh.report.ExtentTestManager;
import com.epam.training.iryna_chekh.user.User;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;


public class SortingTest {
    private static WebDriver driver;
    private final static User currentUser = UserGenerator.createUser();
    private static final Logger LOGGER = Logger.getLogger(SortingTest.class.getName());


    @Parameters("browser")
    @BeforeMethod
    public void initDriver(@Optional("nothing") String browser) {
        if (browser.equals("nothing")) {
            browser = System.getProperty("browser");
        }

        LOGGER.log(Level.CONFIG, "Initializing driver for browser: {0}", browser);

        SingletonDriver.getInstance(browser);
        driver = SingletonDriver.getDriver();

        LOGGER.log(Level.INFO, "Driver initialized successfully");
    }

    private ProductsPage login(User user) {
        LOGGER.log(Level.INFO, "Logging in user: {0}", user.getUserName());

        return new LoginPage(driver)
                .openPage()
                .enterUserNameAndPassword(user)
                .login();
    }

    @Test(description = "Testing the functionality of sorting product names in A–Z order")
    public void shouldSortTitlesAscending() {
        ExtentTestManager.createTest("Log in user and sort products by their " +
                "titles ascending " + driver);
        LOGGER.log(Level.INFO, "Executing test: shouldSortTitlesAscending");

        List<String> actualNames = login(currentUser)
                .sortElements(SortingParameter.TITLE_ASC)
                .getProductsNames();

        assertThat(actualNames).containsExactlyElementsOf(
                actualNames.stream()
                        .sorted()
                        .collect(Collectors.toList()));
    }

    @Test(description = "Testing the functionality of sorting product names in Z–A order")
    public void shouldSortTitlesDescending() {
        ExtentTestManager.createTest("Log in user and sort products by their titles descending " + driver);

        LOGGER.log(Level.INFO, "Executing test: shouldSortTitlesDescending");

        List<String> actualNames = login(currentUser)
                .sortElements(SortingParameter.TITLE_DES)
                .getProductsNames();

        assertThat(actualNames).containsExactlyElementsOf(
                actualNames.stream()
                        .sorted(Comparator.reverseOrder())
                        .collect(Collectors.toList()));
    }

    @Test(description = "Verification of product sorting by ascending price")
    public void shouldSortPricesAscending() {
        ExtentTestManager.createTest("Log in user and sort products by their prices ascending " + driver);

        LOGGER.log(Level.INFO, "Executing test: shouldSortPricesAscending");

        List<Double> actualPrices = login(currentUser)
                .sortElements(SortingParameter.PRICE_ASC)
                .getProductsPrice();

        assertThat(actualPrices).containsExactlyElementsOf(
                actualPrices.stream()
                        .sorted()
                        .collect(Collectors.toList()));
    }

    @Test(description = "Verification of product sorting by descending price")
    public void shouldSortPricesDescending() {
        ExtentTestManager.createTest("Log in user and sort products by their prices descending " + driver);

        LOGGER.log(Level.INFO, "Executing test: shouldSortPricesDescending");

        List<Double> actualPrices = login(currentUser)
                .sortElements(SortingParameter.PRICE_DES)
                .getProductsPrice();

        assertThat(actualPrices).containsExactlyElementsOf(
                actualPrices.stream()
                        .sorted(Comparator.reverseOrder())
                        .collect(Collectors.toList()));
    }


    @AfterMethod
    public void logResultAndCloseDriver(ITestResult result) {
        LOGGER.log(Level.INFO, "Finalizing test: {0}", result.getName());

        TestResultLogger.logResult(result);
        SingletonDriver.closeDriver();

        LOGGER.log(Level.INFO, "Driver closed");
    }


    @AfterSuite
    public void generateReport() {
        LOGGER.log(Level.INFO, "Flushing ExtentReport after suite execution");
        ExtentTestManager.flushReport();
    }

}
