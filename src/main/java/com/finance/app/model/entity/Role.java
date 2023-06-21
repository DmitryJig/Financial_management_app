package com.finance.app.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "roles")
@NoArgsConstructor
public class Role {
    @Id
    @SequenceGenerator(
            name = "ROLES_SEC",
            sequenceName = "ROLES_SEC",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ROLES_SEC"
    )
    @Column(name = "id")
    private Long id;
    @Column(name = "role_name")
    private String roleName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id != null && id.equals(role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
