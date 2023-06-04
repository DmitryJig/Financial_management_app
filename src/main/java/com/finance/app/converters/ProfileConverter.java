package com.finance.app.converters;

import com.finance.app.dto.ProfileDTO;
import com.finance.app.entity.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileConverter {
    public Profile dtoToEntity(ProfileDTO dto){
        return new Profile(dto.getId(),dto.getProfileName(),dto.getBalance(), dto.getUser());
    }
    public ProfileDTO entityToDTO(Profile entity){
        return new ProfileDTO(entity.getId(), entity.getProfileName(), entity.getBalance(),entity.getUser());
    }
}
