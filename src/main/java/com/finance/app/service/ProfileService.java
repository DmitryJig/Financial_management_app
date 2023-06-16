package com.finance.app.service;

import com.finance.app.exception.ResourceNotFoundException;
import com.finance.app.model.entity.Profile;
import com.finance.app.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserService userService;

    public List<Profile> findAll(){
        return profileRepository.findAll();
    }

    public Profile findById(Long id){
        return profileRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format("Profile with id = %d not found", id)));
    }

    @Transactional
    public Profile save(Profile profile){
        //userService.save(profile.getUser()); Юзер сохранятеся при добавлении профиля.. если новый, и его не нужно добавлять отдельно.
        return profileRepository.save(profile);
    }

    public void delete(Profile profile){
        profileRepository.delete(profile);
    }
}
