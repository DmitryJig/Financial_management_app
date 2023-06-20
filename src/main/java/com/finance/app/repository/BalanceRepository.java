package com.finance.app.repository;

import com.finance.app.model.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

    Optional<Balance> findByIdAndProfileId(Long balanceId, Long profileId);
}
