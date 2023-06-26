package com.finance.app.model.dto;

import lombok.Value;

@Value
public class ProfileReq {
    String profileName;
    Long userId;
}
