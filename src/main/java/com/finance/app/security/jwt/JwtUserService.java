package com.finance.app.security.jwt;

import com.finance.app.converters.JwtUserConverter;
import com.finance.app.model.dto.AccountCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtUserService {
    private final DaoAuthenticationProvider authenticationProvider;
    private final JwtUserConverter jwtUserConverter;


    public JwtUser getAuthenticated(AccountCredentials credentials) {
        UsernamePasswordAuthenticationToken creds = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(),
                credentials.getPassword());

        Authentication auth = authenticationProvider.authenticate(creds);
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return jwtUserConverter.userToJwtUser(userDetails);

    }

}
