package com.finance.app.converters;

import com.finance.app.AppApplication;
import com.finance.app.model.dto.UserDto;
import com.finance.app.model.entity.User;
import com.finance.app.service.UserDetailServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AppApplication.class)
@ActiveProfiles("prod")
class UserConverterTest {
    @Autowired
    UserConverter userConverter;
    @Autowired
    UserDetailServiceImpl userDetailService;

    @Test
    void entityToDtoTest() {
        User testUser = getTestUser();
        userDetailService.save(testUser);
        UserDto testUserDto = userConverter.entityToDto(testUser);
        Assertions.assertEquals(testUser.getId(), testUserDto.getId());
        Assertions.assertEquals(testUser.getUsername(), testUserDto.getUsername());
        Assertions.assertEquals(testUser.getEmail(), testUserDto.getEmail());
        userDetailService.delete(testUser);
    }

    private User getTestUser() {
        User user = new User();
        user.setUsername("TestName");
        user.setPassword("TestPassword");
        user.setEmail("TestEmail@gmail.com");
        return user;
    }
}