package com.finance.app.model.dto;


import lombok.Value;

/**
 * Класс UserDto для начала с минимальным набором (чтобы не показывать лишние данные)
 */
@Value
public class UserDto {
    Long id;
    String username;
    String email;
}
