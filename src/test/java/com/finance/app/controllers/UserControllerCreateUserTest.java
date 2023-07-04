package com.finance.app.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.app.controllers.annotation.IT;
import com.finance.app.model.dto.RegUserDto;
import com.finance.app.model.entity.Role;
import com.finance.app.model.entity.User;
import com.finance.app.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IT
public class UserControllerCreateUserTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    UserService userService;

    @Test
    void createUserTest() throws Exception {
        User testUser = getTestUser();
        RegUserDto regUserDto =
                new RegUserDto(
                        testUser.getUsername(),
                        testUser.getPassword(),
                        testUser.getEmail(),
                        testUser.getRoles()
                );
        mockMvc.perform(
                        post("/create")
                                .content(objectMapper.writeValueAsString(regUserDto))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());
        testUser = userService.getUserByUsername(testUser.getUsername());
        userService.delete(testUser);
    }

    private User getTestUser() {
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("ROLE_MANAGER");

        User user = new User();
        user.setUsername("TestName");
        user.setPassword("TestPassword");
        user.setEmail("TestEmail@gmail.com");
        user.setRoles(Set.of(role));
        return user;
    }

}
