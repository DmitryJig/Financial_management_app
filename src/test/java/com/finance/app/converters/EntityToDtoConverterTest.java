package com.finance.app.converters;


import com.finance.app.AppApplication;
import com.finance.app.dto.ProfileDTO;
import com.finance.app.entity.Profile;
import com.finance.app.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AppApplication.class)
class EntityToDtoConverterTest {

    @Test
    void entityToDto() {
        Profile profile = getTestProfile();
        ProfileDTO profileDTO = ProfileConverter.entityToDTO(profile);
        Assertions.assertEquals(profile.getId(), profileDTO.getId());
        Assertions.assertEquals(profile.getProfileName(), profileDTO.getProfileName());
        Assertions.assertEquals(profile.getBalance(), profileDTO.getBalance());
        Assertions.assertEquals(profile.getUser(), profileDTO.getUser());
    }

    @Test
    void dtoToEntity() {
        ProfileDTO profileDTO = getTestProfileDTO();
        Profile profile = ProfileConverter.dtoToEntity(profileDTO);
        Assertions.assertEquals(profile.getId(), profileDTO.getId());
        Assertions.assertEquals(profile.getProfileName(), profileDTO.getProfileName());
        Assertions.assertEquals(profile.getBalance(), profileDTO.getBalance());
        Assertions.assertEquals(profile.getUser(), profileDTO.getUser());
    }

    private Profile getTestProfile() {
        Profile profile = new Profile();
        profile.setProfileName("TestProfileName");
        profile.setUser(getTestUser());
        profile.setBalance(BigDecimal.valueOf(13010));
        return profile;
    }

    private ProfileDTO getTestProfileDTO() {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setProfileName("TestProfileName");
        profileDTO.setUser(getTestUser());
        profileDTO.setBalance(BigDecimal.valueOf(13010));
        return profileDTO;
    }

    private User getTestUser() {
        User user = new User();
        user.setUsername("TestName");
        user.setPassword("TestPassword");
        user.setEmail("TestEmail@gmail.com");
        return user;
    }
}
