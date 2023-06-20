package com.finance.app.converters;

import com.finance.app.model.dto.BalanceDto;
import com.finance.app.model.entity.Balance;
import com.finance.app.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BalanceConverter {

    private final ProfileService profileService;

    public BalanceDto toDto(Balance entity) {
        return new BalanceDto(
                entity.getId(),
                entity.getAmount(),
                entity.getProfile().getId()
        );
    }

    public Balance toEntity(BalanceDto dto) {
        var balance = new Balance();
        balance.setId(dto.getId());
        balance.setAmount(dto.getAmount());
        balance.setProfile(profileService.findById(dto.getProfileId()));
        return balance;
    }
}
