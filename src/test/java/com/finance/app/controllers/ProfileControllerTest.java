package com.finance.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.app.controllers.annotation.IT;
import com.finance.app.exception.ResourceNotFoundException;
import com.finance.app.model.dto.ProfileDto;
import com.finance.app.model.dto.ProfileReq;
import com.finance.app.service.ProfileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static com.jayway.jsonpath.JsonPath.*;

@IT
@WithMockUser(username = "test@gmail.com", password = "test", roles = {"ADMIN", "USER"})
public class ProfileControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ProfileService profileService;

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/api/v1/users/1/profiles/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.profileName").value("Family"))
                .andExpect(jsonPath("$.balanceId").value(1))
                .andExpect(jsonPath("$.userId").value(1));
    }

    @Test
    void getProfilesByUserIdTest() throws Exception {
        int size = profileService.findAll(1L).getProfileDtos().size();

        mockMvc.perform(get("/api/v1/users/1/profiles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.profileDtos", hasSize(size)));
    }

    @Test
    void createProfileTest() throws Exception {
        var profileReq = getProfileReq();

        MvcResult result = mockMvc.perform(
                        post("/api/v1/users/1/profiles")
                                .content(objectMapper.writeValueAsString(profileReq))
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        String savedProfileName = read(response, "$.profileName");
        Long userId = Long.parseLong(read(response, "$.userId").toString());

        assertAll(
                () -> assertEquals(profileReq.getProfileName(), savedProfileName),
                () -> assertEquals(profileReq.getUserId(), userId)
        );

    }

    @Test
    void deleteById() throws Exception {
        var profileReq = getProfileReq();
        ProfileDto testProfile = profileService.save(profileReq);
        Long profileId = testProfile.getId();

        mockMvc.perform(delete("/api/v1/users/1/profiles/" + profileId))
                .andExpect(status().isOk());

        assertThrows(ResourceNotFoundException.class, () -> profileService.findByProfileId(profileId));
    }

    private ProfileDto getTestProfile() {
        return new ProfileDto(
                null,
                "TestName",
                1L,
                1L
        );
    }
    private ProfileReq getProfileReq() {
        return new ProfileReq(
                "testProfile",
                1L
        );
    }
}
