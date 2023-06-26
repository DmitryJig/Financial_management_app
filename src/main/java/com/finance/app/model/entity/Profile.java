package com.finance.app.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
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
    @OneToOne(mappedBy = "profile", cascade = CascadeType.REMOVE)
    private Balance balance;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "profile")
    private Collection<Transaction> transactions;
    @OneToMany(mappedBy = "profile")
    private Collection<Category> categories;

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
