package com.jaqueline.qa.pages;

import com.jaqueline.qa.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    //LOCATORS
    private By userInput = By.id("user-name");
    private By passInput = By.id("password");
    private By loginBtn = By.id("login-button");
    private By errorMsg = By.cssSelector("[data-test='error']");

    // CONSTRUCTOR
    // Recibir driver desde el Test para poder controlarlo
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUser(String text) {
        //driver.findElement(userInput).sendKeys(texto);
        webActions.type(userInput, text,"Input user");
    }

    public void enterPassword(String text) {
        //driver.findElement(passInput).sendKeys(texto);
        webActions.type(passInput, text,"Input password");
    }

    public void clickLogin() {
        //driver.findElement(loginBtn).click();
        webActions.click(loginBtn,"Login button");
    }

    public String getErrorText() {
        //return driver.findElement(errorMsg).getText();
        return webActions.getText(errorMsg,"Message Error");
    }

    public void login(String user, String pass) {
        enterUser(user);
        enterPassword(pass);
        clickLogin();
    }
}
