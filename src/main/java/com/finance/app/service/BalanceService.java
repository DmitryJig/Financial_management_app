package com.finance.app.service;

import com.finance.app.converters.BalanceConverter;
import com.finance.app.exception.ResourceNotFoundException;
import com.finance.app.model.dto.BalanceDto;
import com.finance.app.model.entity.Balance;
import com.finance.app.repository.BalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceService {
    private final BalanceRepository balanceRepository;
    private final BalanceConverter balanceConverter;

    public Balance findById(Long id) {
        return balanceRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format("Balance with id = %d not found", id)));
    }

    public void deleteById(Long id) {
        balanceRepository.deleteById(id);
    }

    public BalanceDto save(Balance balance){
        return balanceConverter.toDto(balanceRepository.save(balance));
    }


}
