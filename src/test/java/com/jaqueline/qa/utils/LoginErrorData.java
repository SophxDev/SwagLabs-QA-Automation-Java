package com.jaqueline.qa.utils;

import lombok.Data;

@Data
public class LoginErrorData {
    private String user;
    private String password;
    private String messageError;
}
