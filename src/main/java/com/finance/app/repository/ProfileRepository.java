package com.finance.app.repository;

import com.finance.app.model.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByIdAndUserId(Long profileId, Long userId);
    //void deleteByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);
}
