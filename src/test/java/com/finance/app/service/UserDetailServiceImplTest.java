package com.finance.app.service;

import com.finance.app.AppApplication;
import com.finance.app.entity.User;
import com.finance.app.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AppApplication.class)
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

    private User getTestUser(){
        User user = new User();
        user.setUsername("TestName");
        user.setPassword("TestPassword");
        user.setEmail("TestEmail@gmail.com");
        return user;
    }
}