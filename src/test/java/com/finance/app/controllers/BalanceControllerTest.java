package com.finance.app.controllers;

import com.finance.app.controllers.annotation.IT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IT
@WithMockUser(username = "test@gmail.com", password = "test", roles = {"ADMIN", "USER"})
public class BalanceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void findByProfileIdAndBalanceIdTest() throws Exception {
        mockMvc.perform(get("/api/v1/users/1/profiles/1/balances/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.amount").value(100))
                .andExpect(jsonPath("$.profileId").value(1));

    }
}
