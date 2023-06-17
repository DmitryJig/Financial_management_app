package com.finance.app.converters;

import com.finance.app.model.dto.ProfileDTO;
import com.finance.app.model.entity.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileConverter {
    public Profile dtoToEntity(ProfileDTO dto) {
        return new Profile(dto.getId(), dto.getProfileName(), dto.getBalance(), dto.getUser(), dto.getTransactions());
    }

    public ProfileDTO entityToDTO(Profile entity) {
        return new ProfileDTO(entity.getId(), entity.getProfileName(), entity.getBalance(), entity.getUser(), entity.getTransactions());
    }
}
