package com.finance.app.converters;

import com.finance.app.model.dto.BalanceDto;
import com.finance.app.model.entity.Balance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BalanceConverter {


    public BalanceDto toDto(Balance entity) {
        return new BalanceDto(
                entity.getId(),
                entity.getAmount(),
                entity.getProfile().getId()
        );
    }

}
