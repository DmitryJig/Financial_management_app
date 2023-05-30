package com.finance.app.converters;


import com.finance.app.DTO.TransactionDTO;
import com.finance.app.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {
    public Transaction dtoToEntity(TransactionDTO dto) {
        return new Transaction(dto.getId(), dto.getDescription(), dto.getAmount(), dto.getType(), dto.getCreated());
    }

    public TransactionDTO entityToDto(Transaction transaction) {
        return new TransactionDTO(transaction.getId(), transaction.getDescription(), transaction.getAmount(), transaction.getType(), transaction.getCreated());
    }
}
