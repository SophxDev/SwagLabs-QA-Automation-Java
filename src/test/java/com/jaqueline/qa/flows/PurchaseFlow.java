package com.jaqueline.qa.flows;
import com.jaqueline.qa.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PurchaseFlow {
    private WebDriver driver;

    // Constructor
    public PurchaseFlow(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String user, String pass) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user, pass);
    }

    public void addProductsToCart(String... products) {
        InventoryPage inventoryPage = new InventoryPage(driver);
        for (String prod : products) {
            inventoryPage.addProductToCart(prod);
        }
        inventoryPage.goToCart();
    }

    public void proceedToCheckout() {
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.verifyPageCart(), "We're not in the cart");
        cartPage.clickCheckout();
    }

    public void completeShippingInformation(String name, String lastName, String zip) {
        CheckoutInfoPage infoPage = new CheckoutInfoPage(driver);
        infoPage.fillInformationShipping(name, lastName, zip);
    }

    public void confirmarOrden() {
        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        overviewPage.finalizePurchase();
    }

    public String obtenerMensajeFinal() {
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage.getConfirmationMessage();
    }
}
