package com.finance.app.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "profiles")
public class Profile {
    @Id
    @SequenceGenerator(
            name = "PROFILES_SEC",
            sequenceName = "PROFILES_SEC",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "PROFILES_SEC"
    )
    @Column(name = "id")
    private Long id;
    @Column(name = "profile_name")
    private String profileName;
    @Column(name = "balance")
    private BigDecimal balance;    //TODO вынести balance в отдельный класс Entity и реализовать к ней слои доступа к данным и контроллеры
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "profile")
    private Collection<Transaction> transactions;
    @OneToMany(mappedBy = "profile")
    private Collection<Category> categories;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return id != null && id.equals(profile.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
