package com.finance.app.model.dto;

import com.finance.app.model.entity.Profile;
import com.finance.app.model.entity.ReportEntity;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Value
public class ReportDto {

    Long id;
    LocalDateTime startDate;
    LocalDateTime endDate;

    List<ReportEntity> incomeByCategory;
    List<ReportEntity> expenseByCategory;

    BigDecimal totalExpenses;
    BigDecimal totalIncome;
    BigDecimal balance;

    Profile profile;
}
