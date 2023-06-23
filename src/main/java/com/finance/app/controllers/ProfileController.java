package com.finance.app.controllers;


import com.finance.app.model.dto.ProfileDto;
import com.finance.app.model.dto.ProfileResponse;
import com.finance.app.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/users/{userId}/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/{profileId}")
    public ProfileDto getProfile(@PathVariable Long profileId) {
        return profileService.findByProfileId(profileId);
    }

    @GetMapping
    public ProfileResponse getProfiles(@PathVariable Long userId) {
        return profileService.findAll(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileDto createProfile(@RequestBody ProfileDto profDto) {
        return profileService.save(profDto);
    }

    @DeleteMapping("/{profileId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProfile(@PathVariable Long profileId) {
        profileService.deleteByProfileId(profileId);
    }
}
