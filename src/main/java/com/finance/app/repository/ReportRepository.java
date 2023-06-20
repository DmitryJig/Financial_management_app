package com.finance.app.repository;

import com.finance.app.model.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    Optional<Report> findAllByIdAndProfileId(Long id, Long profileId);
}
