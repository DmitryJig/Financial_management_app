package com.finance.app.controllers;

import com.finance.app.converters.BalanceConverter;
import com.finance.app.model.dto.BalanceDto;
import com.finance.app.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/balance/{profileId}")
public class BalanceController {
    private final BalanceConverter balanceConverter;
    private final BalanceService balanceService;

    @GetMapping("/{balanceId}")
    public BalanceDto getBalance(@PathVariable Long profileId, @PathVariable Long balanceId) {
        return balanceConverter.toDto(balanceService.findByProfileId(balanceId, profileId));
    }

    @DeleteMapping("/{balanceId}")
    public void deleteBalance(@PathVariable Long profileId, @PathVariable Long balanceId) {
        balanceService.deleteByBalanceIdAndProfileId(balanceId, profileId);
    }

    @PostMapping
    public BalanceDto saveOrUpdate(@RequestBody BalanceDto balanceDto) {
        return balanceService.save(balanceConverter.toEntity(balanceDto));

    }
}
