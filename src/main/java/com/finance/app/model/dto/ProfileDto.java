package com.finance.app.model.dto;


import lombok.Value;

@Value
public class ProfileDto {
    Long id;
    String profileName;
    Long balance;
    Long userId;

}
