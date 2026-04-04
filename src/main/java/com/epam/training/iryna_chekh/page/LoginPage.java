package com.epam.training.iryna_chekh.page;

import com.epam.training.iryna_chekh.ResourcesDataReader;
import com.epam.training.iryna_chekh.user.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.logging.Level;
import java.util.logging.Logger;


public class LoginPage extends AbstractPage {
    private static final Logger LOGGER = Logger.getLogger(LoginPage.class.getName());

    @FindBy(css = "input[id=login-button]")
    private WebElement loginButton;

    @FindBy(css = "input[id=password]")
    private WebElement userPasswordInput;

    public LoginPage(WebDriver driver) {
        super(driver);
        LOGGER.log(Level.INFO, "Initialize LoginPage with driver: {0}", driver.toString());
        PageFactory.initElements(driver, this);
    }

    public LoginPage openPage() {
        LOGGER.log(Level.CONFIG, "Get page URL from config.properties");
        String url = ResourcesDataReader.getData("login.page.url");
        LOGGER.log(Level.INFO, "Opening login page: {0}", url);
        driver.get(url);
        LOGGER.log(Level.INFO, "Login page opened successfully");
        return this;
    }

    public LoginPage enterUserNameAndPassword(User user) {
        LOGGER.log(Level.INFO, "Waiting up to 10 seconds " +
                "for username input to be visible");
        WebElement userNameInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input[id=user-name]")
                )
        );
        LOGGER.log(Level.INFO, "Entering username");
        userNameInput.sendKeys(user.getUserName());
        LOGGER.log(Level.INFO, "Entering password");
        userPasswordInput.sendKeys(user.getUserPassword());
        return this;
    }

    public ProductsPage login() {
        LOGGER.log(Level.INFO, "Clicking login button");
        loginButton.click();
        LOGGER.log(Level.INFO, "Login submitted, navigating to ProductsPage");
        return new ProductsPage(driver);
    }
}
