package com.finance.app.controllers.impl;

import com.finance.app.controllers.LoginController;
import com.finance.app.model.dto.AccountCredentials;
import com.finance.app.model.dto.LoginResponse;
import com.finance.app.security.jwt.JwtService;
import com.finance.app.security.jwt.JwtUser;
import com.finance.app.security.jwt.JwtUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginControllerImpl implements LoginController {
    private final JwtService jwtService;
    private final JwtUserService jwtUserService;

    @Override
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse getToken(@RequestBody @Validated AccountCredentials credentials) {
        JwtUser jwtUser = jwtUserService.getAuthenticated(credentials);
        return jwtService.getLoginResponse(jwtUser);
    }
}
