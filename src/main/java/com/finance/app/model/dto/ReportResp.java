package com.finance.app.model.dto;

import com.finance.app.model.entity.Category;
import com.finance.app.model.entity.Transaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ReportResp {

    private LocalDate startDate;
    private LocalDate endDate;
    private Map<Category, List<Transaction>> incomeByCategory;
    private Map<Category, List<Transaction>> expenseByCategory;
    private Long profileId;
    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
    private BigDecimal balance;



}
