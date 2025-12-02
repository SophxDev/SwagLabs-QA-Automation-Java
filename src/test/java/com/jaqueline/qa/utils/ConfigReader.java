package com.jaqueline.qa.utils;

import java.io.FileInputStream;
import java.util.Properties;
import java.io.IOException;

public class ConfigReader {
    private static Properties properties;

    // Bloque estático: Se ejecuta una sola vez cuando se carga la clase en memoria.
    static {
        try {

            String path = "src/test/resources/config.properties";

            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("CRITICAL ERROR: Could not load config.properties file");
        }
    }

    // Metodo público para pedir valores: ConfigReader.getProperty("url")
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
