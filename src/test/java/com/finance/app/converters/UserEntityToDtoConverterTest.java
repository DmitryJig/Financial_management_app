package com.finance.app.converters;


import com.finance.app.AppApplication;
import com.finance.app.dto.UserDTO;
import com.finance.app.entity.Role;
import com.finance.app.entity.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AppApplication.class)
@ActiveProfiles("prod")
class UserEntityToDtoConverterTest {

    @Test
    void entityToDto() {
        User user = getTestUser();
        UserDTO userDTO = UserConverter.entityToDTO(user);
        Assertions.assertEquals(userDTO.getId(), user.getId());
        Assertions.assertEquals(userDTO.getUsername(), user.getUsername());
        Assertions.assertEquals(userDTO.getPassword(), null);
        Assertions.assertEquals(userDTO.getEmail(), user.getEmail());
        Assertions.assertEquals(userDTO.getRoles(), user.getRoles());
        Assertions.assertEquals(userDTO.getProfiles(), user.getProfiles());
    }

    @Test
    void dtoToEntity() {
        UserDTO userDTO = getTestUserDTO();
        User user = UserConverter.dtoToEntity(userDTO);
        Assertions.assertEquals(user.getId(), userDTO.getId());
        Assertions.assertEquals(user.getUsername(), userDTO.getUsername());
        Assertions.assertEquals(user.getPassword(), userDTO.getPassword());
        Assertions.assertEquals(user.getEmail(), userDTO.getEmail());
        Assertions.assertEquals(user.getRoles(), userDTO.getRoles());
        Assertions.assertEquals(user.getProfiles(), user.getProfiles());

    }

    private UserDTO getTestUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("TestName");
        userDTO.setPassword("TestPassword");
        userDTO.setEmail("TestEmail@gmail.com");
        userDTO.setRoles(Collections.singleton(getTestRole()));
        userDTO.setProfiles(Collections.singleton(null));
        return userDTO;
    }

    private User getTestUser() {
        User user = new User();
        user.setUsername("TestName");
        user.setPassword("TestPassword");
        user.setEmail("TestEmail@gmail.com");
        user.setRoles(Collections.singleton(getTestRole()));
        user.setProfiles(Collections.singleton(null));
        return user;
    }

    private Role getTestRole() {
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("TestRole");
        return role;
    }
}
