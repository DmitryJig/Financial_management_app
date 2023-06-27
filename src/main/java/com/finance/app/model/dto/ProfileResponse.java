package com.finance.app.model.dto;

import lombok.Value;

import java.util.List;

@Value
public class ProfileResponse {
    List<ProfileDto> profileDtos;
}
