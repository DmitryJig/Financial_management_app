package com.finance.app.service;

import com.finance.app.model.entity.Category;
import com.finance.app.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("prod")
public class CategoryServiceTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;
    @Test
    public void testGetAllCategories() {
        createTestCategory();
        List<Category> result = categoryService.getAllCategories();
        Assertions.assertEquals(4, result.size());
        Assertions.assertEquals("Category Test", result.get(3).getTitle());
        categoryService.deleteCategoryByTitle("Category Test");
    }
    @Test
    public void testGetCategoryById() {
        Category category = createTestCategory();
        Optional<Category> result = categoryService.getCategoryById(category.getId());
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Category Test", result.get().getTitle());
        categoryService.deleteCategoryByTitle("Category Test");
    }
    @Test
    public void testGetCategoryByTitle() {
        createTestCategory();
        Optional<Category> result = categoryService.getCategoryByTitle("Category Test");
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Category Test", result.get().getTitle());
        categoryService.deleteCategoryByTitle("Category Test");
    }
    @Test
    public void testCreateCategory() {
        Category category = new Category();
        category.setTitle("Category Test");
        Category result = categoryService.createOrUpdateCategory(category);
        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals("Category Test", result.getTitle());
        categoryService.deleteCategoryByTitle("Category Test");
    }
    @Test
    public void testUpdateCategory() {
        Category category = createTestCategory();
        category.setTitle("Updated Category");
        Category result = categoryService.createOrUpdateCategory(category);
        Assertions.assertEquals("Updated Category", result.getTitle());
        categoryService.deleteCategoryByTitle("Updated Category");
    }
    @Test
    public void testDeleteCategory() {
        Category category = createTestCategory();
        categoryService.deleteCategoryById(category.getId());
        Optional<Category> result = categoryRepository.findById(category.getId());
        Assertions.assertFalse(result.isPresent());
    }
    @Test
    public void testDeleteCategoryByTitle() {
        createTestCategory();
        categoryService.deleteCategoryByTitle("Category Test");
        List<Category> result = categoryRepository.findAll();
        Assertions.assertEquals(3, result.size());
    }
        // С помощью этого метода создаем и сохраняем тестовые категории в базе данных
    private Category createTestCategory() {
        Category category = new Category();
        category.setTitle("Category Test");
        return categoryRepository.save(category);
    }
}
