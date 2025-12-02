package com.jaqueline.qa.utils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j //log
public class WebActions {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public WebActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    //CLICK SEGURO
    public void click(By locator, String descriptionElement) {
        try {

            wait.until(ExpectedConditions.elementToBeClickable(locator));
            driver.findElement(locator).click();
            log.info("Click made on: {}", descriptionElement);

        } catch (Exception e) {
            log.error("Failure to click on: {}", descriptionElement, e);
            throw e; // Relanzar para que el test falle
        }
    }

    //ESCRIBIR TEXTO
    public void type(By locator, String text, String elementDescription) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            WebElement element = driver.findElement(locator);
            element.clear(); // Buena práctica: limpiar antes de escribir
            element.sendKeys(text);
            log.info("Was writen '{}' in field: {}", text, elementDescription);
        } catch (Exception e) {
            log.error("FAiled to write in: {}", elementDescription, e);
            throw e;
        }
    }

    // OBTENER TEXTO
    public String getText(By locator, String elementDescription) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            String text = driver.findElement(locator).getText();
            log.info("Text obtained from {}: '{}'", elementDescription, text);
            return text;
        } catch (Exception e) {
            log.error("Failure to retrieve text from: {}", elementDescription, e);
            throw e;
        }
    }

    //VERIFICAR VISIBILIDAD
    public boolean isVisible(By locator, String elementDescription) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            log.info("Visible element: {}", elementDescription);
            return true;
        } catch (Exception e) {
            log.warn("Element not visible: {}", elementDescription);
            return false;
        }
    }
}
