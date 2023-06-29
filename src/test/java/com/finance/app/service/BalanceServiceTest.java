package com.finance.app.service;

import com.finance.app.converters.BalanceConverter;
import com.finance.app.model.dto.BalanceDto;
import com.finance.app.model.entity.Balance;
import com.finance.app.repository.BalanceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class BalanceServiceTest {

    @Autowired
    private BalanceRepository balanceRepository;
    @Autowired
    private BalanceConverter balanceConverter;
    @Autowired
    private BalanceService balanceService;


    @Test
    void testFindByBalanceIdAndProfileId() {
        BalanceDto balanceDto = balanceService.findByBalanceIdAndProfileId(1l, 1l);
        assertAll(
                () -> assertEquals(1L, balanceDto.getId()),
                () -> assertEquals(1000, balanceDto.getAmount())
        );
    }

    @Test
    Balance testCreateNewBalance() {
        Balance balance = new Balance();
        balance.setAmount(BigDecimal.valueOf(1000));
        return balance;
    }
}