package com.finance.app.service;


import com.finance.app.converters.TransactionConverter;
import com.finance.app.exception.ResourceNotFoundException;
import com.finance.app.model.dto.TransactionDto;
import com.finance.app.model.entity.Transaction;
import com.finance.app.model.enums.TypeOfTransaction;
import com.finance.app.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@ActiveProfiles("test")
public class TransactionServiceTest {
    @Autowired
    TransactionService transactionService;
    @Autowired
    TransactionConverter transactionConverter;
    @Autowired
    TransactionRepository transactionRepository;

    @Test
    void testSave() {
        Transaction transaction = getTestTransaction();
        TransactionDto savedTransaction = transactionService.save(transaction);
        assertAll(
                () -> assertNotNull(savedTransaction.getId()),
                () -> assertEquals(transaction.getDescription(), savedTransaction.getDescription())
        );
    }

    @Test
    void testGetAllByProfileId() {
        List<Transaction> transactions = transactionService.getAllByProfileId(1L);
        List<Transaction> transactionRepo = transactionRepository.findAllByProfileId(1L);
        assertAll(
                ()->assertEquals(transactions.size(),transactionRepo.size()),
                ()->assertEquals(transactions.get(1).getDescription(),transactionRepo.get(1).getDescription())
        );
    }

    @Test
    void testGetById() {
        TransactionDto transactionDto = transactionService.getById(1L);
        Transaction transactionRepo = transactionRepository.findById(1L).get();
        assertEquals(transactionDto.getDescription(),transactionRepo.getDescription());
    }

    @Test
    public void testDeleteByIdAndProfileId() {
        TransactionDto transactionTest = transactionService.getById(1L);
        assertNotNull(transactionTest);
        transactionService.deleteByIdAndProfileId(transactionTest.getId(),transactionTest.getProfileId());
        assertThrows(ResourceNotFoundException.class,
                () -> transactionService.getById(1L));
    }

    private Transaction getTestTransaction() {
        return transactionConverter.toEntity(new TransactionDto(null,
                "TestTransaction",
                BigDecimal.valueOf(300),
                TypeOfTransaction.INCOME,
                LocalDate.now(),
                2L,
                1L));
    }
}
