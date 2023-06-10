package com.finance.app.controllers;


import com.finance.app.converters.ProfileConverter;
import com.finance.app.model.dto.ProfileDTO;
import com.finance.app.model.entity.Profile;
import com.finance.app.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/profile")
public class ProfileController {
    @Autowired
    ProfileService profileService;

    @GetMapping("/{id}")
    public ProfileDTO getProfile(@PathVariable Long id){
        Profile profile = profileService.findById(id);
        return ProfileConverter.entityToDTO(profile);
    }

    @PostMapping()
    public ProfileDTO saveOrUpdate(@RequestBody Profile profile) {
        profileService.save(profile);
        return ProfileConverter.entityToDTO(profile);
    }

    @DeleteMapping("/{id}")
    public void deleteProfile(@PathVariable Profile profile) {
        profileService.delete(profile);
    }
}
