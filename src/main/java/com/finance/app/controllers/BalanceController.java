package com.finance.app.controllers;

import com.finance.app.model.dto.BalanceDto;
import com.finance.app.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/{userId}/profiles/{profileId}/balances")
public class BalanceController {
    private final BalanceService balanceService;

    @GetMapping("/{balanceId}")
    public BalanceDto getBalance(@PathVariable Long profileId, @PathVariable Long balanceId) {
        return balanceService.findByBalanceIdAndProfileId(balanceId, profileId);
    }

}
