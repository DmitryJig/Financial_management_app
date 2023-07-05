package com.finance.app.repository;

import com.finance.app.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    //Реализуем возможность получения категории по имени

    List<Category> findAllByProfileId(Long profileId);
    Optional<Category> findByTitle(String title);
}
