package com.finance.app.model.dto;


import lombok.Value;


@Value
public class RegistrationUserDto {
    String username;
    String password;
    String confirmPassword;
    String email;
}
