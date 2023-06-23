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
@RequestMapping("/api/v1/users/{userId}/transactions/{profileId}")
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionConverter transactionConverter;

    @GetMapping
    public List<TransactionDto> getAllTransaction(@PathVariable Long profileId) {
        return transactionService.getAllByProfileId(profileId).stream().map(transactionConverter::toDto).collect(Collectors.toList());
    }

    @DeleteMapping("/{transactionId}")
    public void deleteTransactionById(@PathVariable Long profileId, @PathVariable Long transactionId) {
        transactionService.deleteByIdAndProfileId(transactionId, profileId);
    }

    @PostMapping
    public TransactionDto addTransaction(@RequestBody TransactionDto dto) {
        return transactionService.save(transactionConverter.toEntity(dto));
    }

}
