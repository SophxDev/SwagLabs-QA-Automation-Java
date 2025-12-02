package com.jaqueline.qa.pages;

import com.jaqueline.qa.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInfoPage extends BasePage {
    private By firstNameInput = By.id("first-name");
    private By lastNameInput = By.id("last-name");
    private By zipInput = By.id("postal-code");
    private By continueBtn = By.id("continue");

    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
    }

    public void fillInformationShipping(String name, String lastName, String zip) {

        webActions.type(firstNameInput, name, "Input first Name");
        webActions.type(lastNameInput, lastName, "Input LastName");
        webActions.type(zipInput, zip, "Input Postal Code");
        webActions.click(continueBtn, "Continue Button");
    }
}
