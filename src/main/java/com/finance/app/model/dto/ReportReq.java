package com.finance.app.model.dto;

import lombok.Value;

import java.time.LocalDate;

@Value
public class ReportReq {
    LocalDate startDate;
    LocalDate endDate;
    boolean addIncome;
    boolean addExpense;
    Long profileId;
}
