package com.epam.training.iryna_chekh.page;

import com.epam.training.iryna_chekh.SortingParameter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ProductsPage extends AbstractPage {
    private static final Logger LOGGER = Logger.getLogger(ProductsPage.class.getName());


    @FindBy(css = "div[data-test=inventory-item-name]")
    List<WebElement> productNames;

    @FindBy(css = "div[data-test=inventory-item-price]")
    List<WebElement> productPrices;

    public ProductsPage(WebDriver driver) {
        super(driver);
        LOGGER.log(Level.INFO, "Initialize ProductsPage with driver: {0}", driver.toString());
        PageFactory.initElements(driver, this);
    }

    public ProductsPage sortElements(SortingParameter sortingParameter) {
        LOGGER.log(Level.INFO, "Waiting up to 10 seconds " +
                "for sort dropdown to be visible");
        WebElement sortDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("select[data-test='product-sort-container']")
                )
        );

        LOGGER.log(Level.INFO, "Applying sorting: {0}", sortingParameter.name());
        new Select(sortDropdown).selectByValue(
                sortingParameter.getValue()
        );
        return this;
    }

    public List<String> getProductsNames() {
        LOGGER.log(Level.INFO, "Getting products names");
        return productNames.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<Double> getProductsPrice() {
        LOGGER.log(Level.INFO, "Parsing product prices");
        return productPrices.stream()
                .map(e -> Double.parseDouble(
                                e.getText().replace("$", "")
                        )
                )
                .collect(Collectors.toList());
    }
}
