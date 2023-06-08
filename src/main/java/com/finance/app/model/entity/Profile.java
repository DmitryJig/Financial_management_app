package com.finance.app.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @OneToMany(mappedBy = "profile")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<Transaction> transactions;

    // todo при создании сущности categories надо будет добавить коллекцию категорий
}
