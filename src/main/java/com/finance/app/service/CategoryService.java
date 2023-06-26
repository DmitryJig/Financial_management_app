package com.finance.app.service;

import com.finance.app.converters.CategoryConverter;
import com.finance.app.exception.ResourceNotFoundException;
import com.finance.app.model.dto.CategoryDto;
import com.finance.app.model.entity.Category;
import com.finance.app.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Объект не найден с таким ID " + id));
    }

    public Category getCategoryByTitle(String title) {
        return categoryRepository.findByTitle(title).orElseThrow(() ->
                new ResourceNotFoundException("Объект не найден с таким названием " + title));
    }

    public CategoryDto createOrUpdateCategory(CategoryDto categoryDto) {
        Category category = categoryConverter.dtoToEntity(categoryDto);
        categoryRepository.save(category);
        return categoryConverter.entityToDTO(category);
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
    public void deleteCategoryByTitle(String categoryName) {
        Optional<Category> category = categoryRepository.findByTitle(categoryName);
        category.ifPresent(categoryRepository::delete);
    }
}
