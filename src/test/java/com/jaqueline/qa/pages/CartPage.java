package com.jaqueline.qa.pages;

import com.jaqueline.qa.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage  extends BasePage {

    //Localizadores
    private By checkoutBtn = By.id("checkout");
    private By cartTitle = By.className("title");
    private By removeBtn = By.xpath("//button[text()='Remove']");

    private By cartItems = By.className("cart_item");

    public CartPage(WebDriver driver){
        super(driver);
    }

    public boolean verifyPageCart(){
        return webActions.isVisible(cartTitle, "Titulo 'Your Cart'");
    }

    public void clickCheckout(){
        webActions.click(checkoutBtn, "Boton Checkout");
    }

    public void clickRemoveProduct(){
        webActions.click(removeBtn, "Boton Remove (Primer item)");
    }

    public int getNumberOfItems(){

        return driver.findElements(cartItems).size();
    }

}
