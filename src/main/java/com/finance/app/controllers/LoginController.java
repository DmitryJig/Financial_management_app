package com.finance.app.controllers;

import com.finance.app.model.dto.AccountCredentials;
import com.finance.app.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController implements LoginControllerInterface {
    private final JwtService jwtService;
    private final DaoAuthenticationProvider authenticationProvider;

    @Override
    public ResponseEntity<?> getToken(@RequestBody @Validated AccountCredentials credentials) {
        UsernamePasswordAuthenticationToken creds = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(),
                credentials.getPassword());

        Authentication auth = authenticationProvider.authenticate(creds);
        UserDetails user = (UserDetails) auth.getPrincipal();
        String jwts = jwtService.getToken(user);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts)
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
                .build();
    }
}

