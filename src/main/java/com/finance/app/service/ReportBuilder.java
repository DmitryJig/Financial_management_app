package com.finance.app.service;

import com.finance.app.model.dto.ReportReq;
import com.finance.app.model.dto.ReportResp;
import com.finance.app.model.entity.Category;
import com.finance.app.model.entity.Transaction;
import com.finance.app.model.enums.TypeOfTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReportBuilder {

    private final TransactionService transactionService;

    public ReportResp build(ReportReq reportReq) {
        ReportResp reportResp = new ReportResp();
        reportResp.setStartDate(reportReq.getStartDate());
        reportResp.setEndDate(reportReq.getEndDate());
        reportResp.setProfileId(reportReq.getProfileId());

        Map<Category, List<Transaction>> incomeByCategory = transactionService.getTransactionByProfileAndDateRange(reportReq.getProfileId(), reportReq.getStartDate(), reportReq.getEndDate())
                .stream().filter(t -> t.getType().equals(TypeOfTransaction.INCOME)).collect(Collectors.groupingBy(Transaction::getCategory));

        Map<Category, List<Transaction>> expenseByCategory = transactionService.getTransactionByProfileAndDateRange(reportReq.getProfileId(), reportReq.getStartDate(), reportReq.getEndDate())
                .stream().filter(t -> t.getType().equals(TypeOfTransaction.EXPENSE)).collect(Collectors.groupingBy(Transaction::getCategory));

        reportResp.setIncomeByCategory(incomeByCategory);
        reportResp.setExpenseByCategory(expenseByCategory);

        reportResp.setTotalIncome(getSumOfTransaction(getListOfTransaction(incomeByCategory.values())));
        reportResp.setTotalExpense(getSumOfTransaction(getListOfTransaction(expenseByCategory.values())));

        reportResp.setBalance(reportResp.getTotalIncome().subtract(reportResp.getTotalExpense()));

        return reportResp;
    }

    private BigDecimal getSumOfTransaction(List<Transaction> transactions) {
        return transactions.stream().map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<Transaction> getListOfTransaction(Collection<List<Transaction>> transactions) {
        return transactions.stream().flatMap(List::stream).collect(Collectors.toList());
    }

}
