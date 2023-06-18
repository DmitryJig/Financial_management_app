package com.finance.app.service;

import com.finance.app.model.dto.ReportReq;
import com.finance.app.model.dto.ReportResp;
import com.finance.app.model.entity.Report;
import com.finance.app.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final ReportBuilder reportBuilder;

    public void saveReport(Report report) {
        reportRepository.save(report);
    }

    public ReportResp createReport(ReportReq reportReq) {
        return reportBuilder.build(reportReq);
    }

}
