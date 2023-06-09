package com.finance.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
;
import com.finance.app.controllers.annotation.IT;
import com.finance.app.exception.ResourceNotFoundException;
import com.finance.app.model.dto.RegUserDto;
import com.finance.app.model.dto.UserDto;
import com.finance.app.model.entity.Role;
import com.finance.app.model.entity.User;
import com.finance.app.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@IT
@WithMockUser(username = "test@gmail.com", password = "test", roles = {"ADMIN", "USER"})
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    UserService userService;

    @Test
    @DisplayName("Ожидаем тело ответа [{\"id\":1,\"username\":\"Admin\",\"email\":\"admin@mail.com\"},{\"id\":2,\"username\":\"Manager\",\"email\":\"manager@mail.com\"}]")
    void findAll() throws Exception {
        UserDto testUserDto1 = new UserDto(1L, "Admin", "admin@mail.com");
        UserDto testUserDto2 = new UserDto(2L, "Manager", "manager@mail.com");

        mockMvc.perform(get("/api/v1/users/1/all"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(testUserDto1, testUserDto2))));
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/api/v1/users/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("Admin"))
                .andExpect(jsonPath("$.email").value("admin@mail.com"));
    }

    /**
     * В этом методе ожидаем ошибку при вызове пользователя с несуществующим id
     * и проверяем что эта ошибка ResourceNotFoundException
     */
    @Test
    void findByIdWhenNotExistUser() throws Exception {
            mockMvc.perform(get("/api/v1/users/5"))
                    .andExpect(status().isNotFound())
                    .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof ResourceNotFoundException));
    }

    @Test
    void deleteById() throws Exception {
        User testUser = getTestUser();
        userService.save(testUser);
        Long testId = testUser.getId();
        mockMvc.perform(delete("/api/v1/users/" + testId))
                .andExpect(status().isOk());
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> userService.findById(testId));
    }

    private User getTestUser(){
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