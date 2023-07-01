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
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class TransactionServiceTest {
    @Autowired
    TransactionService transactionService;
    @Autowired
    TransactionConverter transactionConverter;
    @Autowired
    TransactionRepository transactionRepository;

    @Test
    void testSave() {
        Transaction transaction = getTestTransaction("test",LocalDate.now());
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
                () -> assertEquals(transactions.size(), transactionRepo.size()),
                () -> assertEquals(transactions.get(1).getDescription(), transactionRepo.get(1).getDescription())
        );
    }

    @Test
    void testGetById() {
        TransactionDto transactionDto = transactionService.getById(1L);
        Transaction transactionRepo = transactionRepository.findById(1L).get();
        assertEquals(transactionDto.getDescription(), transactionRepo.getDescription());
    }

    @Test
    public void testDeleteByIdAndProfileId() {
        TransactionDto transactionTest = transactionService.getById(1L);
        assertNotNull(transactionTest);
        transactionService.deleteByIdAndProfileId(transactionTest.getId(), transactionTest.getProfileId());
        assertThrows(ResourceNotFoundException.class,
                () -> transactionService.getById(1L));
    }

    @Test
    public void testRangeTransaction() {
        saveRangeTransaction();
        List<Transaction> list = transactionService.getTransactionByProfileAndDateRange(2L, LocalDate.of(2023, 1, 1), LocalDate.of(2023, 5, 4));
        List<Transaction> listRepo = transactionRepository.findByProfileIdAndCreatedBetween(2L, LocalDate.of(2023, 1, 1), LocalDate.of(2023, 5, 4));
        assertAll(
                () -> assertEquals(list.size(), listRepo.size()),
                () -> assertEquals(list.get(1).getDescription(), listRepo.get(1).getDescription())
        );
    }

    private void saveRangeTransaction() {
        for (int i = 0; i < 5; i++) {
            transactionService.save(getTestTransaction(String.valueOf(i), LocalDate.of(LocalDate.now().getYear(), i + 1, 1)));
        }
    }

    private Transaction getTestTransaction(String name, LocalDate localDate) {
        return transactionConverter.toEntity(new TransactionDto(null,
                "TestTransaction" + name,
                BigDecimal.valueOf(300),
                TypeOfTransaction.INCOME,
                localDate,
                2L,
                1L));
    }
}
