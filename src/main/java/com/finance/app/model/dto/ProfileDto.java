package com.finance.app.model.dto;


import lombok.Value;

import java.math.BigDecimal;

@Value
public class ProfileDto {
    Long id;
    String profileName;
    BigDecimal balance;
    Long userId;
}
