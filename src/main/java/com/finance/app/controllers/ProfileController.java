package com.finance.app.controllers;


import com.finance.app.converters.ProfileConverter;
import com.finance.app.model.dto.ProfileDto;
import com.finance.app.service.ProfileService;
import com.finance.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/profile/{userId}")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileConverter profileConverter;
    private final ProfileService profileService;

    @GetMapping
    public List<ProfileDto> getAllProfilesByUserId(@PathVariable Long userId){
        return profileService.findAllByUserId(userId).stream().map(profileConverter::entityToDto).collect(Collectors.toList());
    }

    @GetMapping("/{profileId}")
    public ProfileDto getProfile(@PathVariable Long userId, @PathVariable Long profileId) {
        return profileConverter.entityToDto(profileService.findByIdAndProfileId(profileId, userId));
    }

    @PostMapping
    public ProfileDto saveOrUpdate(@RequestBody ProfileDto profDto) {
        return profileService.save(profileConverter.dtoToEntity(profDto));
    }

    @DeleteMapping("/{profileId}")
    public void deleteProfile(@PathVariable Long profileId) {
        profileService.deleteByProfileId(profileId);
    }
}
