package com.finance.app.controllers;

import com.finance.app.converters.CategoryConverter;
import com.finance.app.model.dto.CategoryDto;
import com.finance.app.model.entity.Category;
import com.finance.app.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryConverter categoryConverter;

    @GetMapping
    public List<CategoryDto> findAll(){
        return categoryService.getAllCategories().stream().map(categoryConverter::entityToDTO).collect(Collectors.toList());
    }

    @GetMapping("/byId/{id}")
    public CategoryDto findById(@PathVariable Long id){
        Category category = categoryService.getCategoryById(id);
        return categoryConverter.entityToDTO(category);
    }

    @GetMapping("/byTitle/{title}")
    public CategoryDto findByName(@PathVariable String title){
        Category category = categoryService.getCategoryByTitle(title);
        return categoryConverter.entityToDTO(category);
    }


    @PostMapping
    public CategoryDto createOrUpdateCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.createOrUpdateCategory(categoryDto);
    }

    @DeleteMapping("/byId/{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }

    @DeleteMapping("/byTitle/{title}")
    public void deleteCategoryByName(@PathVariable String title) {
        categoryService.deleteCategoryByTitle(title);
    }
}
