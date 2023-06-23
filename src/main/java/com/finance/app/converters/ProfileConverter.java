package com.finance.app.converters;

import com.finance.app.model.dto.ProfileDto;
import com.finance.app.model.entity.Profile;
import com.finance.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileConverter {
    private final UserService userService;

    public Profile dtoToEntity(ProfileDto dto) {
        var profile = new Profile();
        profile.setId(dto.getId());
        profile.setProfileName(dto.getProfileName());
        profile.setUser(userService.findById(dto.getId()));
        return profile;
    }

    public ProfileDto entityToDto(Profile entity) {
        return new ProfileDto(
                entity.getId(),
                entity.getProfileName(),
                entity.getBalance().getId(),
                entity.getUser().getId());
    }
}
