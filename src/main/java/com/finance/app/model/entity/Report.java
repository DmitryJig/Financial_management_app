package com.finance.app.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reports")
public class Report {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "start_date")
    LocalDateTime startDate;
    @Column(name = "end_date")
    LocalDateTime endDate;

    @ElementCollection
    @CollectionTable(name = "report_income_by_category")
    List<ReportEntity> incomeByCategory;

    @ElementCollection
    @CollectionTable(name = "report_expense_by_category")
    List<ReportEntity> expenseByCategory;

    @Column(name = "total_expenses")
    BigDecimal totalExpenses;
    @Column(name = "total_income")
    BigDecimal totalIncome;
    @Column(name = "balance")
    BigDecimal balance;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Profile profile;

}
