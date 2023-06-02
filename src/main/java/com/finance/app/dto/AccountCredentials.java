package com.finance.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Информация о пользователе.")
public class AccountCredentials {

    @Schema(description = "Имя пользователя.", example = "john_doe")
    @NotBlank(message = "Поле логина не может быть пустым.")
    private String username;
    @Schema(description = "Пароль пользователя.", example = "Pa$$w0rd")
    @NotBlank(message = "Поле пароля не может быть пустым.")
    @Size(min = 3, max = 128, message = "Пароль должен содержать минимум 3 символа и не более 128.")
    private String password;
}
