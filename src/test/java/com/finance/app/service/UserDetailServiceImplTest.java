package com.finance.app.service;

import com.finance.app.AppApplication;
import com.finance.app.entity.User;
import com.finance.app.exception.ResourceNotFoundException;
import com.finance.app.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AppApplication.class)
@ActiveProfiles("prod")
class UserDetailServiceImplTest {
    @Autowired
    UserDetailServiceImpl userService;

    @Autowired
    UserRepository userRepository;

    @Test
    void findAllTest() {
        List<User> usersFromRepo = userRepository.findAll();
        List<User> usersFromService = userService.findAll();
        Assertions.assertEquals(usersFromRepo.get(0).getId(), usersFromService.get(0).getId());
        Assertions.assertEquals(usersFromRepo.get(0), usersFromService.get(0));
        Assertions.assertIterableEquals(usersFromRepo, usersFromService);
    }

    @Test
    void saveTest() {
        User user = getTestUser();
        user = userService.save(user);
        Assertions.assertNotNull(user.getId());
        userRepository.delete(user);
    }

    @Test
    void deleteTest(){
        User testUser = getTestUser();
        testUser = userRepository.save(testUser);
        Long testId = testUser.getId();
        Assertions.assertNotNull(testUser.getId());
        userService.delete(testUser);
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> userService.findById(testId));
    }

    private User getTestUser(){
        User user = new User();
        user.setUsername("TestName");
        user.setPassword("TestPassword");
        user.setEmail("TestEmail@gmail.com");
        return user;
    }
}