package com.finance.app.converters;

import com.finance.app.model.dto.CategoryDto;
import com.finance.app.model.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    public CategoryDto entityToDTO(Category entity) {
        return new CategoryDto(entity.getId(),entity.getTitle(),entity.getTransactions());
    }

    public Category dtoToEntity(CategoryDto dto) {
        return new Category(dto.getId(),dto.getTitle(),dto.getTransactions());
    }
}
