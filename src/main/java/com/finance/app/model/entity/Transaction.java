package com.finance.app.model.entity;

import com.finance.app.model.enums.TypeOfTransaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Id
    @SequenceGenerator(
            name = "TRANSACTIONS_SEC",
            sequenceName = "TRANSACTIONS_SEC",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "TRANSACTIONS_SEC"
    )
    @Column(name = "id")
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "amount")
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeOfTransaction type;
    @Column(name = "created")
    private LocalDate created;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


}
