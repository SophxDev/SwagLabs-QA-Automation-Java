package com.jaqueline.qa.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverManager {
    // ThreadLocal: ejecucion paralela
    // Cada hilo (test) tendrá su propia copia del driver.
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Constructor privado para evitar instanciación (Patrón Singleton implícito)
    private DriverManager() {}

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    public static void unload() {
        driver.remove(); // Limpieza de memoria
    }

    // FACTORY METHOD
    public static void initDriver(String browser) {
        if (getDriver() == null) {
            WebDriver instance = null;

            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    //ARRANQUE
                    //ESTABILIDAD Y LIMPIEZA
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    chromeOptions.addArguments("--incognito");

                    //EVITAR INTERRUPCIONES
                    chromeOptions.addArguments("--disable-notifications");
                    chromeOptions.addArguments("--disable-popup-blocking");
                    chromeOptions.addArguments("--disable-search-engine-choice-screen");
                    chromeOptions.addArguments("--start-maximized");
                    chromeOptions.setExperimentalOption("excludeSwitches", java.util.Collections.singletonList("enable-automation"));

                    boolean isHeadless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));

                    if (isHeadless) {
                        chromeOptions.addArguments("--headless=new");
                        chromeOptions.addArguments("--window-size=1920,1080");
                    }

                    instance = new ChromeDriver(chromeOptions);
                    break;
                case "firefox":
                    instance = new FirefoxDriver();
                    break;
                case "edge":
                    instance = new EdgeDriver();
                    break;
                default:
                    throw new RuntimeException("Browser no soportado: " + browser);
            }

            // Configuraciones globales
            instance.manage().window().maximize();
            instance.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


            setDriver(instance);
        }
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            unload();
        }
    }
}
