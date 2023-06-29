package com.finance.app.model.entity;

import com.finance.app.model.enums.TypeOfTransaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

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
    @NotBlank(message = "Описание не может быть пустым или состоять из одних пробелов")
    @Size(min = 1, max = 255, message = "Описание должно содержать не менее {min} и не более {max} символов")
    @Column(name = "description")
    private String description;
    @NotNull
    @DecimalMin(value = "0.01", inclusive = false, message = "Сумма транзакции должна быть больше нуля")
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
