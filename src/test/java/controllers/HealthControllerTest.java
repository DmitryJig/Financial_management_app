package controllers;


import controllers.annotation.IT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@IT
public class HealthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getHelloTest() throws Exception {
        mockMvc.perform(get("/api/v1"))
                .andExpect(status().isOk())
                .andExpect(content().string("This is a financial management app"));
    }

    @Test
    @WithMockUser(username = "test@gmail.com", password = "test", roles = {"ADMIN", "USER"})
    void getSecuredDataTest() throws Exception {
        mockMvc.perform(get("/api/v1/secured"))
                .andExpect(status().isOk())
                .andExpect(content().string("This is very sensitive information, only for authorized users"));
    }

    @Test
    void getSecuredDataUnauthorizedTest() throws Exception {
        mockMvc.perform(get("/api/v1/secured"))
                .andExpect(status().isUnauthorized());
    }
}
