package com.finance.app.service;

import com.finance.app.AppApplication;
import com.finance.app.exception.ResourceNotFoundException;
import com.finance.app.model.entity.Profile;
import com.finance.app.model.entity.User;
import com.finance.app.repository.ProfileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AppApplication.class)
@ActiveProfiles("test")
public class ProfileServiceTest {
    @Autowired
    ProfileService profileService;
    @Autowired
    ProfileRepository profileRepository;

    @Test
    void findAllTest() {
        List<Profile> profilesFromRepo = profileRepository.findAll();
        List<Profile> profilesFromService = profileService.findAll();
        Assertions.assertEquals(profilesFromRepo.get(0).getId(), profilesFromService.get(0).getId());
        Assertions.assertEquals(profilesFromRepo.get(0), profilesFromService.get(0));
        Assertions.assertIterableEquals(profilesFromRepo, profilesFromService);
    }
//
//    @Test
//    void saveTest() {
//        Profile profile = getTestProfile();
//        profileService.save(profile);
//        Assertions.assertNotNull(profile.getId());
//        profileRepository.delete(profile);
//    }

//    @Test
//    void findByIdTest() {
//        Profile profileFormService = profileService.findById(1l);
//        Profile profileFromRepo = profileRepository.findById(1l).get();
//        Assertions.assertEquals(profileFormService.getId(), profileFromRepo.getId());
//        Assertions.assertEquals(profileFormService, profileFromRepo);
//    }
//
//    @Test
//    void deleteTest() {
//        Profile testProfile = getTestProfile();
//        Long testId = profileRepository.save(testProfile).getId();
//        Assertions.assertNotNull(testProfile.getId());
//        profileService.deleteByProfileId(testId);
//        Assertions.assertThrows(ResourceNotFoundException.class, () -> profileService.findById(testId));
//    }

//    private Profile getTestProfile() {
//        Profile profile = new Profile();
//        profile.setProfileName("TestName");
//        profile.setUser(getTestUser());
//        profile.setBalance(BigDecimal.valueOf(1230));
//        return profile;
//    }

    private User getTestUser() {
        User user = new User();
        user.setUsername("TestName");
        user.setPassword("TestPassword");
        user.setEmail("TestEmail@gmail.com");
        return user;
    }
}
