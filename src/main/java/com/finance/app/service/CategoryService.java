package com.finance.app.service;

import com.finance.app.model.entity.Category;
import com.finance.app.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Optional<Category> getCategoryByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

    public Category createOrUpdateCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
    public void deleteCategoryByTitle(String categoryName) {
        Optional<Category> category = categoryRepository.findByTitle(categoryName);
        category.ifPresent(categoryRepository::delete);
    }
}
