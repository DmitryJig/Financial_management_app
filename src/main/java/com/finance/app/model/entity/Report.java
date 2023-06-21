package com.finance.app.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reports")
public class Report {

    @Id
    @SequenceGenerator(
            name = "REPORTS_SEC",
            sequenceName = "REPORTS_SEC",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "REPORTS_SEC"
    )
    @Column(name = "id")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return id != null && id.equals(report.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
