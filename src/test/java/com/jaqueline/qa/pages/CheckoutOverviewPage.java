package com.jaqueline.qa.pages;

import com.jaqueline.qa.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {

    private By finishBtn = By.id("finish");
    private By summaryTitle = By.className("title");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public void finalizePurchase() {
        webActions.isVisible(summaryTitle, "Title Overview");
        webActions.click(finishBtn, "Finish Button");
    }
}
