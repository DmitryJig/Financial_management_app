package com.finance.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.app.controllers.annotation.IT;
import com.finance.app.converters.TransactionConverter;
import com.finance.app.exception.ResourceNotFoundException;
import com.finance.app.model.dto.TransactionDto;
import com.finance.app.model.entity.Transaction;
import com.finance.app.model.enums.TypeOfTransaction;
import com.finance.app.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.jayway.jsonpath.JsonPath.read;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@IT
@WithMockUser(username = "test@gmail.com", password = "test", roles = {"ADMIN", "USER"})
public class TransactionControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    TransactionService transactionService;
    @Autowired
    TransactionConverter transactionConverter;

    @Test
    public void getAllTransactionTest() throws Exception {
        List<TransactionDto> list = transactionService.getAllByProfileId(1L)
                .stream()
                .map(transactionConverter::toDto)
                .collect(Collectors.toList());
        mockMvc.perform(get("/api/v1/users/1/transactions/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(list)));
    }

    @Test
    public void deleteTransactionByIdTest() throws Exception {
        Transaction testTransaction = getTestTransaction();
        transactionService.save(testTransaction);
        Long transactionId = testTransaction.getId();
        mockMvc.perform(delete("/api/v1/users/1/transactions/2/" + transactionId))
                .andExpect(status().isOk());
        assertThrows(ResourceNotFoundException.class, () -> transactionService.getById(transactionId));
    }

    @Test
    public void addTransactionTest() throws Exception {
        TransactionDto transaction = transactionConverter.toDto(getTestTransaction());
        MvcResult result = mockMvc.perform(
                        post("/api/v1/users/1/transactions/2")
                                .content(objectMapper.writeValueAsString(transaction))
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        String description = read(response, "$.description");
        assertEquals(transaction.getDescription(), description);
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
