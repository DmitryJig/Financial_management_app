package com.finance.app.model.dto;

import lombok.Value;

@Value
public class LoginResponse {
    Long userid;
    String accessToken;
}
