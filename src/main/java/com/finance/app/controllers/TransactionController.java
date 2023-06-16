package com.finance.app.controllers;

import com.finance.app.converters.TransactionConverter;
import com.finance.app.model.dto.TransactionDto;
import com.finance.app.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private TransactionService transactionService;
    private TransactionConverter transactionConverter;

    @GetMapping("/{id}")
    public TransactionDto getTransactionById(@PathVariable Long id) {
        return transactionConverter.entityToDto(transactionService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteTransactionById(@PathVariable Long id){
        transactionService.deleteById(id);
    }

    @GetMappiing("/all")
    public List<TransactionDto> findAll(){
        return transactionService.findAll().stream()
                .map(transactionConverter::entityToDto).collect(Collectors.toList());
    }

    @GetMapping
    public List<TransactionDto> getCurrentProfileTransactions(@RequestHeader String profileName) {
        return transactionService.findTransactionByProfile(profileName).stream()
                .map(transactionConverter::entityToDto).collect(Collectors.toList());
    }
}
