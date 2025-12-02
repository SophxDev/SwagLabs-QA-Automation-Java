package com.jaqueline.qa.tests.checkout;

import com.jaqueline.qa.base.BaseTest;
import com.jaqueline.qa.flows.PurchaseFlow;
import com.jaqueline.qa.pages.CartPage;
import com.jaqueline.qa.utils.ConfigReader;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("E-Commerce")
@Feature("Cart Functionality")
public class CartTest extends BaseTest {

    @Test(description = "Verify that a product can be removed from the cart")
    @Severity(SeverityLevel.NORMAL)
    public void testRemoveProductFromCart() {

        PurchaseFlow purchaseFlow = new PurchaseFlow(getDriver());

        purchaseFlow.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );
        purchaseFlow.addProductsToCart("Sauce Labs Backpack");

        CartPage cartPage = new CartPage(getDriver());

        int itemsbefore = cartPage.getNumberOfItems();
        Assert.assertEquals(itemsbefore, 1, "Error: The cart should have 1 product at the beginning.");

        cartPage.clickRemoveProduct();

        int itemsAfter = cartPage.getNumberOfItems();
        Assert.assertEquals(itemsAfter, 0, "Error: The cart was not emptied after removing the product.");
    }
}
