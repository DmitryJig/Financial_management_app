package com.finance.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@Table(name = "profiles")
public class Profile {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "profile_name")
    private String profileName;
    @Column(name = "balance")
    private BigDecimal balance;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "transaction")
    private Collection<Transaction> transactions;

    // todo  при создании сущности transaction надо будет добавить коллекцию транзакций
    // todo при создании сущности categories надо будет добавить коллекцию категорий
}
