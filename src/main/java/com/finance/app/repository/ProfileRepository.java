package com.finance.app.repository;

import com.finance.app.model.entity.Profile;
import com.finance.app.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    //@Query(value = "select p from Profile p join fetch p.User where p.id=1")
    Optional<Profile> findById (Long id);
}
