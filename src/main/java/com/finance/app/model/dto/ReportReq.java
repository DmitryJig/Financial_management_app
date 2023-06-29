package com.finance.app.model.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Value
public class ReportReq {
    @NotNull(message = "Дата начала не может быть пустой")
    @PastOrPresent(message = "Дата начала должна быть в прошлом или настоящем")
    LocalDate startDate;
    @PastOrPresent(message = "Дата окончания должна быть в прошлом или настоящем")
    @NotNull(message = "Дата окончания не может быть пустой")
    LocalDate endDate;
    boolean addIncome;
    boolean addExpense;
    @NotNull(message = "Идентификатор профиля не может быть пустым")
    @Positive(message = "Идентификатор профиля должен быть положительным числом")
    Long profileId;
}
