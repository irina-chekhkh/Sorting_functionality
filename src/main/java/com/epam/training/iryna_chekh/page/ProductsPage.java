package com.epam.training.iryna_chekh.page;

import com.epam.training.iryna_chekh.SortingParameter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage extends AbstractPage{

    @FindBy(css = "select[data-test=product-sort-container]")
    private WebElement sortingSelector;

    @FindBy(css="div[data-test=inventory-item-name]")
    List<WebElement> productNames;

    @FindBy(css="div[data-test=inventory-item-price]")
    List<WebElement> productPrices;

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public ProductsPage sortElements(SortingParameter sortingParameter){
        new Select(sortingSelector).selectByValue(sortingParameter.getValue());
        return this;
    }

    public List<String> getProductsNames(){
        return productNames.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<Double> getProductsPrice(){
        return productPrices.stream()
                .map(e-> Double.parseDouble(e.getText().replace("$","")))
                .collect(Collectors.toList());
    }
}
