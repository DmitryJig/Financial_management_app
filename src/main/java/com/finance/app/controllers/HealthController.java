package com.finance.app.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@Tag(name = "Health Controller", description = "Эндпойнты для проверки доступности страниц с авторизацией или без.")
public class HealthController {
    @GetMapping
    @Operation(summary = "Получить информацию о приложении без авторизации.", description = "Возвращает приветственное сообщение о доступности страницы.")
    public String getHello() {
        return "This is a financial management app";
    }
    @GetMapping("/secured")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Получить информацию о приложении при авторизации.", description = "Возвращает приветственное сообщение о доступности страницы.")
    public String getSecuredData() {
        return "This is very sensitive information, only for authorized users";
    }
}
