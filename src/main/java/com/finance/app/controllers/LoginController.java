package com.finance.app.controllers;

import com.finance.app.dto.AccountCredentials;
import com.finance.app.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Пользователь", description = "Контроллер страницы отправки логина")
public class LoginController {
    private final JwtService jwtService;
    private final DaoAuthenticationProvider authenticationProvider;


    @PostMapping("/login")
    @Operation(summary = "Отправка логина и пароля")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешная аутентификация и получение токена."),
            @ApiResponse(responseCode = "401", description = "Неверные учетные данные пользователя.")
    })
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
