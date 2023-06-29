package com.finance.app.service;

import com.finance.app.converters.BalanceConverter;
import com.finance.app.exception.ResourceNotFoundException;
import com.finance.app.model.dto.BalanceDto;
import com.finance.app.model.entity.Balance;
import com.finance.app.model.entity.Profile;
import com.finance.app.repository.BalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BalanceService {
    private final BalanceRepository balanceRepository;
    private final BalanceConverter balanceConverter;


    public BalanceDto findByBalanceIdAndProfileId(Long balanceId, Long profileId) {
        Balance balance = balanceRepository.findByIdAndProfileId(balanceId, profileId).orElseThrow(() -> new ResourceNotFoundException(String.format("Balance with id = %d not found", balanceId)));
        return balanceConverter.toDto(balance);
    }


    public Balance createNewBalance(Profile profile) {
        Balance balance = new Balance();
        balance.setProfile(profile);
        balance.setAmount(BigDecimal.ZERO);
        balance = balanceRepository.save(balance);
        return balance;
    }


}
