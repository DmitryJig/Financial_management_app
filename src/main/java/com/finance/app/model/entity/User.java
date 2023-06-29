package com.finance.app.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(
            name = "USERS_SEC",
            sequenceName = "USERS_SEC",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "USERS_SEC"
    )
    @Column(name = "id")
    private Long id;
    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Size(min = 3, max = 100, message = "Имя пользователя должно содержать не менее {min} и не более {max} символов")
    @Column(name = "user_name")
    private String username;
    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 8, max = 100, message = "Пароль должен содержать не менее {min} и не более {max} символов")
    @Column(name = "password")
    private String password;
    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Email должен быть допустимым адресом электронной почты")
    @Column(name = "email")
    private String email;
    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private Set<Role> roles;
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private Collection<Profile> profiles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id != null && id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
