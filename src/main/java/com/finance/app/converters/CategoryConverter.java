package com.finance.app.converters;

import com.finance.app.exception.ResourceNotFoundException;
import com.finance.app.model.dto.CategoryDto;
import com.finance.app.model.entity.Category;
import com.finance.app.model.entity.Profile;
import com.finance.app.repository.ProfileRepository;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    private final ProfileRepository profileRepository;

    public CategoryConverter(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public CategoryDto entityToDTO(Category entity) {
        return new CategoryDto(
                entity.getId(),
                entity.getTitle(),
                entity.getProfile().getId()
        );
    }

    public Category dtoToEntity(CategoryDto dto) {
        var cat = new Category();
        cat.setId(dto.getId());
        cat.setTitle(dto.getTitle());
        if (dto.getProfileId() != null) {
            Profile profile = profileRepository.findById(dto.getProfileId())
                    .orElseThrow(() -> new ResourceNotFoundException("Profile not found with ID: " + dto.getProfileId()));
            cat.setProfile(profile);
        }
        return cat;
    }
}
