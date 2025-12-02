package com.jaqueline.qa.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JsonReader {
    public static <T> List<T> getData(String jsonPath, Class<T> objectClass) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            InputStream input = JsonReader.class.getClassLoader().getResourceAsStream(jsonPath);

            if (input == null) {
                throw new RuntimeException("ERROR: The JSON file was not found in resources: " + jsonPath);
            }

            // Jackson lee directamente del InputStream
            CollectionType listType = mapper.getTypeFactory()
                    .constructCollectionType(List.class, objectClass);

            return mapper.readValue(input, listType);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading JSON: " + jsonPath);
        }
    }
}
