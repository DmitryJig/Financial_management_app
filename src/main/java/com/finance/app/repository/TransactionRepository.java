package com.finance.app.repository;

import com.finance.app.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByProfileIdAndCreatedBetween(Long profileId, LocalDate startDate, LocalDate endDate);

    List<Transaction> findAllByProfileId(Long profileId);
    void deleteByIdAndProfileId(Long id, Long profileId);

}
