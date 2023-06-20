package com.finance.app.repository;

import com.finance.app.model.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByIdAndUserId(Long profileId, Long userId);
    void deleteByIdAndUserId(Long id, Long userId);
}
