package com.jaqueline.qa.pages;

import com.jaqueline.qa.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InventoryPage extends BasePage {
    private By titleProduct = By.className("title");
    private By cartButton = By.className("shopping_cart_link");
    private By productItems = By.className("inventory_item"); // Cada tarjeta de producto
    private By productNames = By.className("inventory_item_name"); // El texto del nombre

    public InventoryPage(WebDriver driver){
        super(driver);
    }

    public boolean esVisible(){

        return webActions.isVisible(titleProduct,"Title of Products");
    }

    public void addProductToCart(String productName) {

        String nombreFormateado = productName.toLowerCase().replace(" ", "-");

        By dinamicButton = By.id("add-to-cart-" + nombreFormateado);

        webActions.click(dinamicButton, "Button Add to Cart of: " + productName);
    }

    public void openProductDetails(String exactProductName) {

        String xpathRobusto = "//div[@class='inventory_item_name' and text()='" + exactProductName + "']";

        By linkTexto = By.xpath(xpathRobusto);

        webActions.click(linkTexto, "Product link: " + exactProductName);
    }

    public void goToCart() {
        webActions.click(cartButton, "Cart icon");
    }

    public int getQuantityProducts() {

        return driver.findElements(productItems).size();
    }

    public boolean verifyProductVisible(String searchedname) {
        List<WebElement> names = driver.findElements(productNames);

        for (WebElement element : names) {
            if (element.getText().equals(searchedname)) {
                return true;
            }
        }
        return false;
    }
}
