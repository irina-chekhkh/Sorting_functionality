package com.epam.training.iryna_chekh.page;

import com.epam.training.iryna_chekh.ResourcesDataReader;
import com.epam.training.iryna_chekh.user.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class LoginPage extends AbstractPage {

    @FindBy(css = "input[id=login-button]")
    private WebElement loginButton;

    @FindBy(css = "input[id=password]")
    private WebElement userPasswordInput;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LoginPage openPage() {
        String url = ResourcesDataReader.getData("login.page.url");
        driver.get(url);
        return this;
    }

    public LoginPage enterUserNameAndPassword(User user) {
        WebElement userNameInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input[id=user-name]")
                )
        );
        userNameInput.sendKeys(user.getUserName());
        userPasswordInput.sendKeys(user.getUserPassword());
        return this;
    }

    public ProductsPage login() {
        loginButton.click();
        return new ProductsPage(driver);
    }
}
