package com.finance.app.model.dto;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Value
public class ProfileReq {
    @NotBlank(message = "Имя профиля не может быть пустым или состоять только из пробелов")
    @Size(min = 1, max = 100, message = "Имя профиля должно содержать от {min} и не более {max} символов")
    String profileName;
    @NotNull(message = "Идентификатор пользователя не может быть пустым")
    @Positive(message = "Идентификатор профиля должен быть положительным числом")
    Long userId;
}
