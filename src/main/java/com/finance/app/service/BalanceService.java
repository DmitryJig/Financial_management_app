package com.finance.app.service;

import com.finance.app.converters.BalanceConverter;
import com.finance.app.exception.ResourceNotFoundException;
import com.finance.app.model.dto.BalanceDto;
import com.finance.app.model.dto.TransactionDto;
import com.finance.app.model.entity.Balance;
import com.finance.app.model.entity.Profile;
import com.finance.app.model.entity.Transaction;
import com.finance.app.model.enums.TypeOfTransaction;
import com.finance.app.repository.BalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BalanceService {
    private final BalanceRepository balanceRepository;
    private final BalanceConverter balanceConverter;
    private final TransactionService transactionService;

    public BalanceDto findById(Long id) {
        return balanceConverter.toDto(balanceRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format("Balance with id = %d not found", id))));
    }

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

    public void updateBalance(TransactionDto transactionDto) {
        Balance balance = balanceRepository.findByProfileId(transactionDto.getProfileId()).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Balance with profile id = %d not found", transactionDto.getProfileId())));
        List<Transaction> transactionList = transactionService.getAllByProfileId(transactionDto.getProfileId());
        BigDecimal amount = BigDecimal.valueOf(0);
        for (Transaction t : transactionList) {
            switch (t.getType()) {
                case INCOME:
                    amount = amount.add(t.getAmount());
                    break;
                case EXPENSE:
                    amount = amount.add(t.getAmount().multiply(BigDecimal.valueOf(-1)));
                    break;
            }
        }
        balance.setAmount(amount);
    }
}
