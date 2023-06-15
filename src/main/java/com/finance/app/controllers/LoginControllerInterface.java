package com.finance.app.controllers;

import com.finance.app.model.dto.AccountCredentials;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Пользователь", description = "Контроллер страницы отправки логина")
public interface LoginControllerInterface {
    @PostMapping("/login")
    @Operation(summary = "Отправка логина и пароля")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешная аутентификация и получение токена."),
            @ApiResponse(responseCode = "401", description = "Неверные учетные данные пользователя.")
    })
    ResponseEntity<?> getToken(@RequestBody @Validated AccountCredentials credentials);
}
