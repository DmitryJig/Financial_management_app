package com.finance.app.service;

import com.finance.app.converters.TransactionConverter;
import com.finance.app.exception.ResourceNotFoundException;
import com.finance.app.model.dto.TransactionDto;
import com.finance.app.model.entity.Transaction;
import com.finance.app.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionConverter transactionConverter;
    private final BalanceService balanceService;

    public TransactionDto save(Transaction transaction) {
        TransactionDto transactionDto = transactionConverter.toDto(transactionRepository.save(transaction));
        balanceService.updateBalance(transactionConverter.toDto(transaction));
        return transactionDto;
    }

    public List<Transaction> getAllByProfileId(Long profileId) {
        return transactionRepository.findAllByProfileId(profileId);
    }

    public TransactionDto getById(Long transactionId) {
        return transactionConverter.toDto(transactionRepository.findById(transactionId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Transaction with id = %d not found", transactionId))
        ));
    }

    @Transactional
    public void deleteByIdAndProfileId(Long id, Long profileId) {
        transactionRepository.deleteByIdAndProfileId(id, profileId);
        balanceService.updateBalance(getById(id));
    }

    public List<Transaction> getTransactionByProfileAndDateRange(Long profileId, LocalDate starDate, LocalDate endDate) {
        return transactionRepository.findByProfileIdAndCreatedBetween(profileId, starDate, endDate);
    }
}
