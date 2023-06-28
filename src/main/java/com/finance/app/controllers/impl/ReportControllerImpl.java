package com.finance.app.controllers.impl;

import com.finance.app.controllers.ReportController;
import com.finance.app.model.dto.ReportReq;
import com.finance.app.model.dto.ReportResp;
import com.finance.app.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users/{userId}/reports")
@RequiredArgsConstructor
public class ReportControllerImpl implements ReportController {
    private final ReportService reportService;

    @Override
    @PostMapping
    public ReportResp getReport(@RequestBody ReportReq reportReq) {
        return reportService.createReport(reportReq);
    }

}