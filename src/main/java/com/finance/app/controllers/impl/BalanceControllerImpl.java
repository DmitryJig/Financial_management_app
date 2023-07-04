package com.finance.app.controllers.impl;

import com.finance.app.controllers.BalanceController;
import com.finance.app.model.dto.BalanceDto;
import com.finance.app.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/{userId}/profiles/{profileId}/balances")
@RequiredArgsConstructor
public class BalanceControllerImpl implements BalanceController {
    private final BalanceService balanceService;

    @Override
    @GetMapping("/{balanceId}")
    public BalanceDto getBalance(@PathVariable Long profileId, @PathVariable Long balanceId, @PathVariable(name = "userId", required = false) Long userId) {
        return balanceService.findByBalanceIdAndProfileId(balanceId, profileId);
    }
}
