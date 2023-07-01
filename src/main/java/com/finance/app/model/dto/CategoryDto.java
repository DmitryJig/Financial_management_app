package com.finance.app.model.dto;


import com.finance.app.model.entity.Profile;
import lombok.Value;


@Value
public class CategoryDto {
    Long id;
    String title;
    Long profileId;
}
