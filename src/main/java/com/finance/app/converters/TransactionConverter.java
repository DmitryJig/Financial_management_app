package com.finance.app.converters;

import com.finance.app.model.dto.TransactionDto;
import com.finance.app.model.entity.Transaction;
import com.finance.app.model.enums.TypeOfTransaction;
import com.finance.app.service.CategoryService;
import com.finance.app.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionConverter {

    private final ProfileService profileService;
    private final CategoryService categoryService;

    public TransactionDto toDto(Transaction entity) {

        return new TransactionDto(
                entity.getId(),
                entity.getDescription(),
                entity.getAmount(),
                entity.getType().name(),
                entity.getCreated(),
                entity.getProfile().getId(),
                entity.getCategory().getId()
        );
    }

    public Transaction toEntity(TransactionDto dto) {
        var transaction = new Transaction();
        transaction.setId(dto.getId());
        transaction.setDescription(dto.getDescription());
        transaction.setAmount(dto.getAmount());
        transaction.setType(TypeOfTransaction.valueOf(dto.getType()));
        transaction.setCreated(dto.getCreated());
        transaction.setProfile(profileService.findById(dto.getProfileId()));
        transaction.setCategory(categoryService.getCategoryById(dto.getCategoryId()));
        return transaction;
    }

}
