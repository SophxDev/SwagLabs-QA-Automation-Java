package com.jaqueline.qa.tests.auth;

import com.jaqueline.qa.base.BaseTest;
import com.jaqueline.qa.pages.InventoryPage;
import com.jaqueline.qa.pages.LoginPage;
import com.jaqueline.qa.utils.JsonReader;
import com.jaqueline.qa.utils.LoginData;
import com.jaqueline.qa.utils.LoginErrorData;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

@Slf4j
@Epic("Authentication Module")
@Feature("Login")
public class LoginTest extends BaseTest {

    //METHOD SUCCESS LOGINS
    @DataProvider(name = "dataLoginJson", parallel = true)
    public Object[][] getDataFromJson() {

        List<LoginData> dataList = JsonReader.getData("data/credentials.json",LoginData.class);
        //Convercion de la Lista a Object[][] (lo que pide TestNG)
        Object[][] testData = new Object[dataList.size()][1];

        for (int i = 0; i < dataList.size(); i++) {
            testData[i][0] = dataList.get(i);
        }
        return testData;
    }

    //METODO PARA LOGIN ERRONEOS - MENSAJES DE ERORR
    // Este metodo devuelve un Array de Arrays (una tabla de datos)
    @DataProvider(name = "negativeDataJson", parallel = true)
    public Object[][] getDataFormNegativeJson() {

        List<LoginErrorData> dataList = JsonReader.getData("data/negative_credentials.json",LoginErrorData.class);

        Object[][] data = new Object[dataList.size()][1];

        for (int i = 0; i < dataList.size(); i++) {
            data[i][0] = dataList.get(i);
        }
        return data;
    }

    @Test(dataProvider = "dataLoginJson")
    @Description("Verify that multiple valid users can log into the system")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Usser enter valid credentials")
    public void testLoginSuccessfulMassive(LoginData data){
        //Intancia y ejecucion Accion (Helper)
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(data.getUser(), data.getPassword());

        //Validacion
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        Assert.assertTrue(inventoryPage.esVisible(), "The login failed for the user: " + data.getUser());
    }

    @Test(dataProvider = "dataLoginJson")
    @Description("Verify that a valid user can log into the system")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User enters valid credentials")
    public void TestLoginSuccessfulUnit(LoginData data){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(data.getUser(), data.getPassword());

        InventoryPage inventoryPage = new InventoryPage(getDriver());
        Assert.assertTrue(inventoryPage.esVisible(), "The login failed for the user: " + data.getUser());

    }

    //TEST MAESTRO DE ERRORES
    @Test(dataProvider = "negativeDataJson")
    @Description("Validate all error scenarios (Locked, Incorrect, Empty)")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginFailedMassive(LoginErrorData data) {

        LoginPage loginPage = new LoginPage(getDriver());

        // Ejecucion de la acción con los datos del JSON
        loginPage.login(data.getUser(), data.getPassword());

        // Validacion contra el mensaje esperado del JSON
        String currentError = loginPage.getErrorText();

        Assert.assertEquals(currentError, data.getMessageError(),
                "The error message does not match for the user: " + data.getUser());
    }
}
