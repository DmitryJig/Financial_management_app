package com.finance.app.controllers;

import com.finance.app.model.dto.AccountCredentials;
import com.finance.app.model.dto.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Пользователь", description = "Контроллер страницы отправки логина")
public interface LoginController {

    @Operation(summary = "Отправка логина и пароля")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешная аутентификация и получение токена."),
            @ApiResponse(responseCode = "401", description = "Неверные учетные данные пользователя.")
    })
    @ResponseStatus(HttpStatus.OK)
    LoginResponse getToken(@RequestBody @Validated AccountCredentials credentials);
}
