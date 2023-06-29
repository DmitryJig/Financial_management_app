package com.finance.app.controllers.impl;

import com.finance.app.controllers.TransactionController;
import com.finance.app.converters.TransactionConverter;
import com.finance.app.model.dto.TransactionDto;
import com.finance.app.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users/{userId}/transactions/{profileId}")
@RequiredArgsConstructor
public class TransactionControllerImpl implements TransactionController {
    private final TransactionService transactionService;
    private final TransactionConverter transactionConverter;

    @Override
    @GetMapping
    public List<TransactionDto> getAllTransaction(@PathVariable Long profileId) {
        return transactionService.getAllByProfileId(profileId)
                .stream()
                .map(transactionConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @DeleteMapping("/{transactionId}")
    public void deleteTransactionById(@PathVariable Long profileId, @PathVariable Long transactionId) {
        transactionService.deleteByIdAndProfileId(transactionId, profileId);
    }

    @Override
    @PostMapping
    public TransactionDto addTransaction(@Valid @RequestBody TransactionDto dto) {
        return transactionService.save(transactionConverter.toEntity(dto));
    }
}