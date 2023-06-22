package com.finance.app.model.dto;


import com.finance.app.model.entity.Role;
import lombok.Value;

import java.util.Set;


@Value
public class RegUserDto {
    String username;
    String password;
    String email;
    Set<Role> roles;
}
