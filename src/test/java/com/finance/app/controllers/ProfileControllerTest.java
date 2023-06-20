package com.finance.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.app.AppApplication;
import com.finance.app.converters.ProfileConverter;
import com.finance.app.exception.ResourceNotFoundException;
import com.finance.app.model.entity.Profile;
import com.finance.app.model.entity.User;
import com.finance.app.service.ProfileService;
import com.finance.app.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AppApplication.class)
@ActiveProfiles("prod")
@AutoConfigureMockMvc
@AutoConfigureWebTestClient
public class ProfileControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ProfileService profileService;
    @Autowired
    UserService userService;
    @Autowired
    ProfileConverter profileConverter;

    @Test
    @WithMockUser(username = "test@gmail.com", password = "test", roles = {"ADMIN", "USER"})
    void findById() throws Exception {
        mockMvc.perform(get("/api/v1/profile/1/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.profileName").value("profileAdmin"))
                .andExpect(jsonPath("$.balance").value(100000.00))
                .andExpect(jsonPath("$.userId").value(1));
    }

    @Test
    @WithMockUser(username = "test@gmail.com", password = "test", roles = {"ADMIN", "USER"})
    void createProfileTest() throws Exception {
        Profile testProfile = getTestProfile();
        testProfile.setUser(userService.save(testProfile.getUser()));
        var profileDto = profileConverter.entityToDto(testProfile);
        mockMvc.perform(
                post("/api/v1/profile/" + profileDto.getUserId())
                        .content(objectMapper.writeValueAsString(profileDto))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        testProfile.setId(profileService.findAll().stream()
                .filter(p -> testProfile.getProfileName().equals(p.getProfileName()))
                .findFirst().get().getId());
        profileService.deleteByProfileId(testProfile.getId());
    }

    @Test
    @WithMockUser(username = "test@gmail.com", password = "test", roles = {"ADMIN", "USER"})
    void deleteById() throws Exception {
        Profile testProfile = getTestProfile();
        testProfile.setId(profileService.save(testProfile).getId());
        Long testId = testProfile.getId();
        mockMvc.perform(delete("/api/v1/profile/" + testProfile.getUser().getId() + "/" + testId))
                .andExpect(status().isOk());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> profileService.findById(testId));
    }

    private Profile getTestProfile() {
        Profile profile = new Profile();
        profile.setProfileName("TestName");
        profile.setUser(getTestUser());
        profile.setBalance(BigDecimal.valueOf(1230));
        return profile;
    }

    private User getTestUser() {
        User user = new User();
        user.setUsername("TestName");
        user.setPassword("TestPassword");
        user.setEmail("TestEmail@gmail.com");
        return user;
    }
}
