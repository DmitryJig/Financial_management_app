package com.finance.app.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @SequenceGenerator(
            name = "CATEGORIES_SEC",
            sequenceName = "CATEGORIES_SEC",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "CATEGORIES_SEC")
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @OneToMany(mappedBy = "category")
    private Collection<Transaction> transactions;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id != null && id.equals(category.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
