package com.finance.app.service;

import com.finance.app.converters.CategoryConverter;
import com.finance.app.model.dto.CategoryDto;
import com.finance.app.model.entity.Category;
import com.finance.app.model.entity.Profile;
import com.finance.app.model.entity.User;
import com.finance.app.repository.CategoryRepository;
import com.finance.app.repository.ProfileRepository;
import com.finance.app.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class CategoryServiceTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryConverter categoryConverter;
    @Test
    public void testGetAllCategories() {
        createTestCategory();
        List<Category> result = categoryService.getAllCategories(1L);
        Assertions.assertEquals(4, result.size());
        Assertions.assertEquals("Category Test", result.get(3).getTitle());
        categoryService.deleteCategoryByTitle("Category Test");
    }
    @Test
    public void testGetCategoryById() {
        Category category = createTestCategory();
        Optional<Category> result = Optional.ofNullable(categoryService.getCategoryById(category.getId()));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Category Test", result.get().getTitle());
        categoryService.deleteCategoryByTitle("Category Test");
    }
    @Test
    public void testGetCategoryByTitle() {
        createTestCategory();
        Optional<Category> result = Optional.ofNullable(categoryService.getCategoryByTitle("Category Test"));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Category Test", result.get().getTitle());
        categoryService.deleteCategoryByTitle("Category Test");
    }
    @Test
    public void testCreateCategory() {
        Category category = createTestCategory();
        category.setTitle("Category Test");
        category = categoryRepository.save(category);
        CategoryDto result = categoryService.createOrUpdateCategory(categoryConverter.entityToDTO(category));
        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals("Category Test", result.getTitle());
        categoryService.deleteCategoryByTitle("Category Test");
    }
    @Test
    public void testUpdateCategory() {
        Category category = createTestCategory();
        category.setTitle("Updated Category");
        category = categoryRepository.save(category);
        CategoryDto result = categoryService.createOrUpdateCategory(categoryConverter.entityToDTO(category));
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
            Profile profile = createTestProfile();
            profileRepository.save(profile);
            category.setProfile(profile);

            return categoryRepository.save(category);
        }
    private Profile createTestProfile() {
        Profile profile = new Profile();
        profile.setProfileName("342332423");
        User user = createTestUser();
        profile.setUser(user);
        return profileRepository.save(profile);
    }

    private User createTestUser() {
        String username = "355435";
        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            return existingUser.get();
        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword("6543464364536");
            user.setEmail("3453454@mail.com");
            return userRepository.save(user);
        }
    }

}
