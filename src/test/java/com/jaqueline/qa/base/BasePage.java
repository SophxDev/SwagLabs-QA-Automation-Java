package com.jaqueline.qa.base;

import com.jaqueline.qa.utils.WebActions;
import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver driver;
    protected WebActions webActions; //Obj que usaran las clases hijas

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.webActions = new WebActions(driver); //Inicializaremos webActions actomaticamente
    }
}
