package com.finance.app.dto;

import com.finance.app.entity.Transaction;
import com.finance.app.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private Long id;
    private String profileName;
    private BigDecimal balance;
    private User user;
    private Collection<Transaction> transactions;
}
