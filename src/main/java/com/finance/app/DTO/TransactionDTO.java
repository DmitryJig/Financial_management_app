package com.finance.app.DTO;


import com.finance.app.entity.TypeOfTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDTO {
    private Long id;
    private String description;
    private int amount;
    private TypeOfTransaction type;
    private LocalDateTime created;
}
