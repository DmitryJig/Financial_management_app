package com.finance.app.model.dto;

import com.finance.app.model.entity.Profile;
import com.finance.app.model.entity.ReportEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private List<ReportEntity> incomeByCategory;
    private List<ReportEntity> expenseByCategory;

    private BigDecimal totalExpenses;
    private BigDecimal totalIncome;
    private BigDecimal balance;

    private Profile profile;
}
