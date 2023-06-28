package com.finance.app.controllers;

import com.finance.app.model.dto.AccountCredentials;
import com.finance.app.model.dto.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Login Controller", description = "Login API")
public interface LoginController {

    @Operation(summary = "Send login and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful authentication and token retrieval."),
            @ApiResponse(responseCode = "401", description = "Invalid user credentials.")
    })
    @ResponseStatus(HttpStatus.OK)
    LoginResponse getToken(@RequestBody @Validated AccountCredentials credentials);
}
