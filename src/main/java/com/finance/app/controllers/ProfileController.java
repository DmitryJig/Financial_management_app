package com.finance.app.controllers;


import com.finance.app.converters.ProfileConverter;
import com.finance.app.model.dto.ProfileDTO;
import com.finance.app.model.entity.Profile;
import com.finance.app.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/profiles")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    private ProfileConverter profileConverter;

    @GetMapping("/{id}")
    public ProfileDTO getProfile(@PathVariable Long id){
        return profileConverter.entityToDTO(profileService.findById(id));
    }

    @PostMapping
    public ProfileDTO saveOrUpdate(@RequestBody ProfileDTO profileDTO) {
        Profile profile = profileConverter.dtoToEntity(profileDTO);
        profileService.save(profile);
        return profileConverter.entityToDTO(profile);
    }

    @DeleteMapping("/{id}")
    public void deleteProfile(@PathVariable Long id) {
        profileService.delete(profileService.findById(id));
    }
}
