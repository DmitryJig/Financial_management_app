package com.finance.app.model.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class BalanceDto {
    Long id;
    BigDecimal amount;
    Long profileId;
}
