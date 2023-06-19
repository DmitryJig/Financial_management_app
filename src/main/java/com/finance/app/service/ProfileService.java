package com.finance.app.service;

import com.finance.app.converters.ProfileConverter;
import com.finance.app.exception.ResourceNotFoundException;
import com.finance.app.model.dto.ProfileDto;
import com.finance.app.model.entity.Profile;
import com.finance.app.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileConverter profileConverter;

    public List<Profile> findAll(){
        return profileRepository.findAll();
    }

    public Profile findByIdAndProfileId(Long profileId, Long userId){
        return profileRepository.findByIdAndUserId(profileId, userId).orElseThrow(()-> new ResourceNotFoundException(String.format("Profile with id = %d not found", profileId)));
    }

    public Profile findById(Long id) {
        return profileRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format("Profile with id = %d not found", id)));
    }

    public ProfileDto save(Profile profile){
        return profileConverter.entityToDto(profileRepository.save(profile));
    }

//    public void deleteByProfileIdAndUserId(Long profileId, Long userId){
//        profileRepository.deleteByIdAndUserId(profileId, userId);
//    }
    public void deleteByProfileIdAndUserId(Profile profile){
        profileRepository.delete(profile);
    }
}
