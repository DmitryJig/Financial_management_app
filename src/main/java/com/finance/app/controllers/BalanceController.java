package com.finance.app.controllers;

import com.finance.app.model.dto.BalanceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Balance Controller", description = "Balance API")
public interface BalanceController {
    @Operation(summary = "Get balance data", security = @SecurityRequirement(name = "JWT"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Balance data",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BalanceDto.class))}),
            @ApiResponse(responseCode = "400", description = "Balance not found", content = @Content),
            @ApiResponse(responseCode = "403", description = "ProfileId and request doesn't match", content = @Content)})
    BalanceDto getBalance(Long profileId, Long balanceId);
}