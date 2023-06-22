package com.finance.app.security.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;


@Getter
@RequiredArgsConstructor
public class JwtUser {
    private final Long id;
    private final List<Long> profilesId;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

}
