package com.finance.app.model.dto;

import lombok.Value;

@Value
public class LoginResponse {
    Long userId;
    String accessToken;
}
