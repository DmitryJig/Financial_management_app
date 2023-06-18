package com.finance.app.controllers;


import com.finance.app.converters.ProfileConverter;
import com.finance.app.model.dto.ProfileDto;
import com.finance.app.service.ProfileService;
import com.finance.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/profile/{userId}")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileConverter profileConverter;
    private final ProfileService profileService;
    private final UserService userService;

    @GetMapping("/{profileId}")
    public ProfileDto getProfile(@PathVariable Long userId, @PathVariable Long profileId) {
        return profileConverter.entityToDto(profileService.findByIdAndProfileId(profileId, userId));
    }

    @PostMapping
    public ProfileDto saveOrUpdate(@RequestBody ProfileDto profDto) {
        return profileService.save(profileConverter.dtoToEntity(profDto));

    }

    @DeleteMapping("/{profileId}")
    public void deleteProfile(@PathVariable Long userId, @PathVariable Long profileId) {
        profileService.deleteByProfileIdAndUserId(profileId, userId);
    }
}
