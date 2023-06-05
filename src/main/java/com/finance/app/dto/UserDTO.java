package com.finance.app.dto;

import com.finance.app.entity.Profile;
import com.finance.app.entity.Role;
import lombok.*;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Collection<Role> roles;
    private Collection<Profile> profiles;
}
