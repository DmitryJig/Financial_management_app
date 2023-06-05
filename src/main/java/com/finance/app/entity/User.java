package com.finance.app.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<Role> roles;
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<Profile> profiles;
}
