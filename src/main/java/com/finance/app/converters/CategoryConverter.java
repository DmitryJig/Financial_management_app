package com.finance.app.converters;

import com.finance.app.model.dto.CategoryDto;
import com.finance.app.model.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    public CategoryDto entityToDTO(Category entity) {
        return new CategoryDto(
                entity.getId(),
                entity.getTitle(),
                entity.getProfile()
        );
    }

    public Category dtoToEntity(CategoryDto dto) {
        var cat = new Category();
        cat.setId(dto.getId());
        cat.setTitle(dto.getTitle());
        cat.setProfile(dto.getProfile());
        return cat;
    }
}
