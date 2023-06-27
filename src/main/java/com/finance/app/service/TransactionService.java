package com.finance.app.service;

import com.finance.app.converters.TransactionConverter;
import com.finance.app.model.dto.TransactionDto;
import com.finance.app.model.entity.Balance;
import com.finance.app.model.entity.Transaction;
import com.finance.app.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionConverter transactionConverter;
    private final BalanceService balanceService;

    public TransactionDto save(Transaction transaction) {
        balanceService.editBalance(transaction.getAmount(),transaction.getProfile().getId());
        //TODO здесь будет логика калькуляции баланса пользователя через бин balanceService, который нужно реализовать

        return transactionConverter.toDto(transactionRepository.save(transaction));
    }

    public List<Transaction> getAllByProfileId(Long profileId) {
        return transactionRepository.findAllByProfileId(profileId);
    }

    public void deleteByIdAndProfileId(Long id, Long profileId) {
       // BigDecimal x = transactionRepository.findById(id).get().getAmount();
       //balanceService.editBalance(transactionRepository.findById(id).get().getAmount());
        //TODO здесь будет логика калькуляции баланса пользователя через бин balanceService, который нужно реализовать

        transactionRepository.deleteByIdAndProfileId(id, profileId);
    }

    public List<Transaction> getTransactionByProfileAndDateRange(Long profileId, LocalDate starDate, LocalDate endDate) {
        return transactionRepository.findByProfileIdAndCreatedBetween(profileId, starDate, endDate);
    }
}
