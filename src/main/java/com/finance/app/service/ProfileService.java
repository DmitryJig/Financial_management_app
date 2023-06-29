package com.finance.app.service;

import com.finance.app.converters.ProfileConverter;
import com.finance.app.exception.ResourceNotFoundException;
import com.finance.app.model.dto.ProfileDto;
import com.finance.app.model.dto.ProfileReq;
import com.finance.app.model.dto.ProfileResponse;
import com.finance.app.model.entity.Balance;
import com.finance.app.model.entity.Profile;
import com.finance.app.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileConverter profileConverter;
    private final BalanceService balanceService;
    private final UserService userService;

    public ProfileResponse findAll(Long userId) {
        List<ProfileDto> profileDtos = profileRepository.findAllByUserId(userId).stream().map(profileConverter::entityToDto).collect(Collectors.toList());
        return new ProfileResponse(profileDtos);
    }

    public ProfileDto findByProfileId(Long profileId) {
        return profileConverter.entityToDto(profileRepository.findById(profileId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Profile with id = %d not found", profileId))));

    }

    public ProfileDto findByProfileIdAndUserId(Long profileId, Long userId) {
        return profileConverter.entityToDto(profileRepository.findByIdAndUserId(profileId, userId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Profile with id = %d not found", profileId))));
    }

    @Transactional
    public ProfileDto save(ProfileReq req) {
        Profile profile = new Profile();
        profile.setProfileName(req.getProfileName());
        profile.setUser(userService.findById(req.getUserId()));

        profile = profileRepository.save(profile);

        Balance balance = balanceService.createNewBalance(profile);
        profile.setBalance(balance);

        return profileConverter.entityToDto(profile);
    }

    @Transactional
    public void deleteByProfileIdAndUserId(Long profileId, Long userId) {
        profileRepository.deleteByIdAndUserId(profileId, userId);
    }
}
