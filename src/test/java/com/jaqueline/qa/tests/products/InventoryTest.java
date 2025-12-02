package com.jaqueline.qa.tests.products;

import com.jaqueline.qa.base.BaseTest;
import com.jaqueline.qa.flows.PurchaseFlow; // Usamos el Flow para el Login rápido
import com.jaqueline.qa.pages.InventoryPage;
import com.jaqueline.qa.utils.ConfigReader;
import com.jaqueline.qa.utils.InventoryData;
import com.jaqueline.qa.utils.JsonReader;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Epic("E-Commerce")
@Feature("Product Catalog")
public class InventoryTest extends BaseTest {

    @Test(description = "Verify that the catalog loads with 6 products and the correct title")
    @Severity(SeverityLevel.CRITICAL)
    public void testValidateInventoryLoad() {

        List<InventoryData> dataList = JsonReader.getData("data/inventory.json", InventoryData.class);

        InventoryData catalogData = dataList.stream()
                .filter(d -> d.getAlias().equals("main_catalog"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Data not found for main_catalog"));

        //Login rápido
        PurchaseFlow flow = new PurchaseFlow(getDriver());

        flow.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        //Instanciar pagina
        InventoryPage inventory = new InventoryPage(getDriver());

        //Validacion de pagina correcta
        Assert.assertTrue(inventory.esVisible(), "Error: The title 'Products' is not displayed");

        //Validacion de cantidad de productos
        int currentAmount = inventory.getQuantityProducts();
        System.out.println("Products found: " + currentAmount);

        Assert.assertEquals(currentAmount, catalogData.getExpectedProductCount(),
                "Error: The catalog product count is incorrect.");

        //Verificar que el producto este visible
        boolean existsProduct = inventory.verifyProductVisible(catalogData.getProductNameToCheck());
        Assert.assertTrue(existsProduct,
                "Error: The product '" + catalogData.getProductNameToCheck() + "' is not listed.");
    }
}
