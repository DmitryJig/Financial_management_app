package com.finance.app.controllers;


import com.finance.app.DTO.TransactionDTO;
import com.finance.app.converters.TransactionConverter;
import com.finance.app.entity.Transaction;
import com.finance.app.services.TransactionServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionConverter transactionConverter;
    private final TransactionServices transactionServices;

    @GetMapping("/{id}")
    public TransactionDTO getTransactionById(@PathVariable Long id) {
        Transaction transaction = transactionServices.findById(id).orElseThrow();
        return transactionConverter.entityToDto(transaction);
    }

    @PostMapping
    public TransactionDTO saveNewTransaction(@RequestBody TransactionDTO transactionDTO) {
        Transaction transaction = transactionConverter.dtoToEntity(transactionDTO);
        transaction = transactionServices.save(transaction);
        return transactionConverter.entityToDto(transaction);
    }

    @PutMapping
    public TransactionDTO updateTransaction(@RequestBody TransactionDTO transactionDTO) {
        Transaction transaction = transactionServices.update(transactionDTO);
        return transactionConverter.entityToDto(transaction);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        transactionServices.deleteById(id);
    }
}
