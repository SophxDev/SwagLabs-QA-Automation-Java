package com.jaqueline.qa.utils;

import lombok.Data;

@Data //anotación de Lombok crea getters, setters y toString automáticamente
public class LoginData {

    private String user;
    private String password;
}
