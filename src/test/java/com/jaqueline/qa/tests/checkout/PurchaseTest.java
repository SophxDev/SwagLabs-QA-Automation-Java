package com.jaqueline.qa.tests.checkout;

import com.jaqueline.qa.base.BaseTest;
import com.jaqueline.qa.flows.PurchaseFlow;
import com.jaqueline.qa.utils.CheckoutData;
import com.jaqueline.qa.utils.ConfigReader;
import com.jaqueline.qa.utils.JsonReader;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Epic("E-Commerce")
@Feature("Purchase Flow")
public class PurchaseTest extends BaseTest {

    @Test(description = "Validate successful end-to-end purchase")
    @Severity(SeverityLevel.CRITICAL)
    public void testPurchaseEndToEnd() {

        List<CheckoutData> dataList = JsonReader.getData("data/checkout.json", CheckoutData.class);
        CheckoutData shippingData = dataList.stream()
                .filter(d -> d.getAlias().equals("standard_user"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Data not found"));

        // Instanciamos la capa de pasos (Business Layer)
        PurchaseFlow purchaseFlow = new PurchaseFlow(getDriver());

        //Login
        purchaseFlow.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        //Selección de productos (¡Mira qué limpio queda!)
        purchaseFlow.addProductsToCart("Sauce Labs Backpack", "Sauce Labs Bike Light");
        //Ir al Checkout
        purchaseFlow.proceedToCheckout();
        //Llenar datos
        purchaseFlow.completeShippingInformation(
                shippingData.getFirstName(),
                shippingData.getLastName(),
                shippingData.getZipCode()
        );
        //Finalizar
        purchaseFlow.confirmarOrden();
        //Validación
        Assert.assertEquals(purchaseFlow.obtenerMensajeFinal(), "Thank you for your order!");
    }
}
