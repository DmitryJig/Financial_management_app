package com.finance.app.controllers;

import com.finance.app.model.dto.TransactionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/{userId}/transactions/{profileId}")
@Tag(name = "Transaction Controller", description = "Transaction API")
public interface TransactionController {
    @Operation(summary = "Get all transactions by profile ID", security = @SecurityRequirement(name = "JWT"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of transactions",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TransactionDto.class))})
    })
    List<TransactionDto> getAllTransaction(Long profileId);

    @Operation(summary = "Delete transaction by ID and profile ID", security = @SecurityRequirement(name = "JWT"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Transaction deleted"),
            @ApiResponse(responseCode = "404", description = "Transaction not found", content = @Content)
    })
    void deleteTransactionById(Long profileId, Long transactionId);

    @Operation(summary = "Add a new transaction", security = @SecurityRequirement(name = "JWT"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added transaction",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TransactionDto.class))})
    })
    TransactionDto addTransaction(TransactionDto dto);

}