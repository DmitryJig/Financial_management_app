package com.finance.app.model.dto;

import com.finance.app.model.entity.Category;
import com.finance.app.model.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private Long id;
    private String description;
    private BigDecimal amount;
    private String type;
    private LocalDateTime created;
    private Profile profile;
    private Category category;
}
