package com.finance.app.converters;


import com.finance.app.DTO.TransactionDTO;
import com.finance.app.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {
    public Transaction dtoToEntity(TransactionDTO dto) {
        return Transaction.builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .amount(dto.getAmount())
                .type(dto.getType())
                .created(dto.getCreated())
                .build();
    }

    public TransactionDTO entityToDto(Transaction transaction) {
        return TransactionDTO.builder()
                .id(transaction.getId())
                .description(transaction.getDescription())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .created(transaction.getCreated())
                .build();
    }
}
