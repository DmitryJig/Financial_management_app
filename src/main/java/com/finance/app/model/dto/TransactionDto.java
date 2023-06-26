package com.finance.app.model.dto;



import com.finance.app.model.enums.TypeOfTransaction;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Value
public class TransactionDto {
    Long id;
    String description;
    BigDecimal amount;
    TypeOfTransaction type;
    LocalDate created;
    Long profileId;
    Long categoryId;
}
