package com.finance.app.controllers.impl;

import com.finance.app.controllers.CategoryController;
import com.finance.app.converters.CategoryConverter;
import com.finance.app.model.dto.CategoryDto;
import com.finance.app.model.entity.Category;
import com.finance.app.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {
    private final CategoryService categoryService;
    private final CategoryConverter categoryConverter;

    @Override
    @GetMapping
    public List<CategoryDto> findAll() {
        return categoryService.getAllCategories()
                .stream()
                .map(categoryConverter::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        return categoryConverter.entityToDTO(category);
    }

    @Override
    @GetMapping("/{title}")
    public CategoryDto findByName(@PathVariable String title) {
        Category category = categoryService.getCategoryByTitle(title);
        return categoryConverter.entityToDTO(category);
    }

    @Override
    @PostMapping
    public CategoryDto createOrUpdateCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return categoryService.createOrUpdateCategory(categoryDto);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }

    @Override
    @DeleteMapping("/{title}")
    public void deleteCategoryByName(@PathVariable String title) {
        categoryService.deleteCategoryByTitle(title);
    }
}
