package com.finance.app.service;

import com.finance.app.model.entity.Profile;
import com.finance.app.exception.ResourceNotFoundException;
import com.finance.app.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserDetailServiceImpl userDetailService;

    public List<Profile> findAll(){
        return profileRepository.findAll();
    }

    public Profile findById(Long id){
        return profileRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format("Profile with id = %d not found", id)));
    }

    public Profile save(Profile profile){
        userDetailService.save(profile.getUser());
        return profileRepository.save(profile);
    }

    public void delete(Profile profile){
        profileRepository.delete(profile);
    }
}
