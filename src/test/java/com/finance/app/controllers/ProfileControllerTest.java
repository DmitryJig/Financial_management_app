package com.finance.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.app.AppApplication;
import com.finance.app.service.ProfileService;
import com.finance.app.service.UserService;
import liquibase.pro.packaged.E;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AppApplication.class)
//@ActiveProfiles("prod")
@AutoConfigureMockMvc
@AutoConfigureWebTestClient
public class ProfileControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(username = "test@gmail.com", password = "test", roles = {"ADMIN", "USER"})
    void findById() throws Exception {
        mockMvc.perform(get("/api/v1/profiles/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.profileName").value("profileAdmin"))
                .andExpect(jsonPath("$.balance").value(100000.00));
    }


}
