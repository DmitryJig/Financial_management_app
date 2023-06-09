package com.finance.app.model.dto;



import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class TransactionDto {
    Long id;
    String description;
    BigDecimal amount;
    String type;
    LocalDate created;
    Long profileId;
    Long categoryId;
}
