package com.finance.app.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Data
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
    @NotBlank(message = "Заголовок категории не может быть пустым")
    @Size(min = 3, max = 100, message = "Заголовок категории должен содержать не менее {min} и не более {max} символов")
    @Column(name = "title")
    private String title;
    @OneToMany(mappedBy = "category")
    private Collection<Transaction> transactions;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
