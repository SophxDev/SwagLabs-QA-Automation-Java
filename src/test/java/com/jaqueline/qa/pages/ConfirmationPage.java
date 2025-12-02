package com.jaqueline.qa.pages;

import com.jaqueline.qa.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage extends BasePage {

    private By headerMessage = By.className("complete-header");

    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public String getConfirmationMessage() {
        return webActions.getText(headerMessage, "Success Message");
    }
}
