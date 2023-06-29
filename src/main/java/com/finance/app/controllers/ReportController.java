package com.finance.app.controllers;

import com.finance.app.model.dto.ReportReq;
import com.finance.app.model.dto.ReportResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/users/{userId}/reports")
@Tag(name = "Report Controller", description = "Report API")
public interface ReportController {
    @Operation(summary = "Get report", security = @SecurityRequirement(name = "JWT"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Report data",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ReportResp.class))})
    })
    ReportResp getReport(ReportReq reportReq);
}