package com.finance.app.controllers.impl;


import com.finance.app.controllers.ProfileController;
import com.finance.app.model.dto.ProfileDto;
import com.finance.app.model.dto.ProfileReq;
import com.finance.app.model.dto.ProfileResponse;
import com.finance.app.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("api/v1/users/{userId}/profiles")
@RequiredArgsConstructor
public class ProfileControllerImpl implements ProfileController {
    private final ProfileService profileService;

    @Override
    @GetMapping("/{profileId}")
    public ProfileDto getProfile(@PathVariable Long userId, @PathVariable Long profileId) {
        return profileService.findByProfileIdAndUserId(profileId, userId);
    }

    @Override
    @GetMapping
    public ProfileResponse getProfiles(@PathVariable Long userId) {
        return profileService.findAll(userId);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileDto createProfile(@Valid @RequestBody ProfileReq req) {
        return profileService.save(req);
    }

    @Override
    @DeleteMapping("/{profileId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProfile(@PathVariable Long profileId, @PathVariable Long userId) {
        profileService.deleteByProfileIdAndUserId(profileId, userId);
    }
}
