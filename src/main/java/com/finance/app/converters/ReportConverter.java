package com.finance.app.converters;

import com.finance.app.model.dto.ReportDto;
import com.finance.app.model.entity.Report;
import org.springframework.stereotype.Component;

@Component
public class ReportConverter {

    public static ReportDto entityToDTO(Report entity) {
        return new ReportDto(entity.getId(),entity.getStartDate(),entity.getEndDate(),entity.getExpenseByCategory(),entity.getIncomeByCategory(),entity.getTotalIncome(),entity.getTotalExpenses(),entity.getBalance(),entity.getProfile());
    }

    public static Report dtoToEntity(ReportDto dto) {
        return new Report(dto.getId(),dto.getStartDate(),dto.getEndDate(),dto.getExpenseByCategory(),dto.getIncomeByCategory(),dto.getTotalIncome(),dto.getTotalExpenses(),dto.getBalance(),dto.getProfile());
    }


}
