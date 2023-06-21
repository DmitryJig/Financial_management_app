package com.finance.app.controllers;

import com.finance.app.model.dto.ReportReq;
import com.finance.app.model.dto.ReportResp;
import com.finance.app.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/reports/{profileId}")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    public ReportResp getReport(@RequestBody ReportReq reportReq) {
        return reportService.createReport(reportReq);
    }
}
