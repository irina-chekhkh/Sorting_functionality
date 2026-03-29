package com.epam.training.iryna_chekh.page;

import com.epam.training.iryna_chekh.driver.Driver;
import com.epam.training.iryna_chekh.user.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {

    @FindBy(css = "input[id=user-name]")
    private WebElement userNameInput;

    @FindBy(css = "input[id=password]")
    private WebElement userPasswordInput;

    @FindBy(css = "input[id=login-button]")
    private WebElement loginButton;

    public LoginPage(Driver driver) {
        super(driver);
        PageFactory.initElements(driver.getDriver(),this);
    }

    public LoginPage openPage(){
        driver.getDriver().get("https://www.saucedemo.com/");
        return this;
    }

    public LoginPage enterUserNameAndPassword(User user){
        userNameInput.sendKeys(user.getUserName());
        userPasswordInput.sendKeys(user.getUserPassword());
        return this;
    }

    public ProductsPage login(){
        loginButton.click();
        return new ProductsPage(driver);
    }
}
