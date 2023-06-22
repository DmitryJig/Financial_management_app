package com.finance.app.service;

import com.finance.app.converters.ProfileConverter;
import com.finance.app.exception.ResourceNotFoundException;
import com.finance.app.model.dto.ProfileDto;
import com.finance.app.model.dto.ProfileResponse;
import com.finance.app.model.entity.Profile;
import com.finance.app.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileConverter profileConverter;

    public ProfileResponse findAll(Long userId) {
        List<ProfileDto> profileDtos = profileRepository.findAllByUserId(userId).stream().map(profileConverter::entityToDto).collect(Collectors.toList());
        return new ProfileResponse(profileDtos);
    }

    public ProfileDto findByProfileId(Long profileId) {
        return profileConverter.entityToDto(profileRepository.findById(profileId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Profile with id = %d not found", profileId))));
    }
    @Transactional
    public ProfileDto save(ProfileDto profileDto) {
        Profile savedProfile =  profileRepository.save(profileConverter.dtoToEntity(profileDto));
        return profileConverter.entityToDto(savedProfile);
    }

    public void deleteByProfileId(Long profileId) {
        profileRepository.deleteById(profileId);
    }
}
