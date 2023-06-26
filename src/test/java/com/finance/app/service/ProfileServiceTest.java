package com.finance.app.service;

import com.finance.app.exception.ResourceNotFoundException;
import com.finance.app.model.dto.ProfileDto;
import com.finance.app.model.dto.ProfileReq;
import com.finance.app.model.dto.ProfileResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ProfileServiceTest {
    @Autowired
    ProfileService profileService;


    @Test
    void findAllTest() {
        ProfileResponse profileResponse = profileService.findAll(1L);
        assertTrue(profileResponse.getProfileDtos().size() > 1);
    }

    @Test
    void saveTest() {
        ProfileReq req = getProfileReq();
        ProfileDto savedProfile = profileService.save(req);
        assertAll(
                () -> assertNotNull(savedProfile.getId()),
                () -> assertEquals(req.getProfileName(), savedProfile.getProfileName())
        );
    }

    @Test
    void findByProfileIdTest() {
        ProfileDto profileDto = profileService.findByProfileId(1l);
        assertAll(
                () -> assertEquals(1L, profileDto.getId()),
                () -> assertEquals("Family", profileDto.getProfileName())

        );
    }

    @Test
    void deleteTest() {
        ProfileDto profileDto = profileService.findByProfileId(3L);

        assertNotNull(profileDto);

        profileService.deleteByProfileId(profileDto.getId());

        assertThrows(ResourceNotFoundException.class, () -> profileService.findByProfileId(profileDto.getId()));
    }

    private ProfileReq getProfileReq() {
        return new ProfileReq(
                "testProfile",
                1L
        );
    }

}
