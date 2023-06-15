package com.finance.app.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "Health Controller", description = "Эндпойнты для проверки доступности страниц с авторизацией или без.")
public interface HealthControllerInterface {
    @GetMapping
    @Operation(summary = "Получить информацию о приложении без авторизации.", description = "Возвращает приветственное сообщение о доступности страницы.")
    String getHello();

    @GetMapping("/secured")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Получить информацию о приложении при авторизации.", description = "Возвращает приветственное сообщение о доступности страницы.")
    String getSecuredData();
}
