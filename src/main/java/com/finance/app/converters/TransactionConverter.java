package com.finance.app.converters;

import com.finance.app.model.dto.TransactionDto;
import com.finance.app.model.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {

    public static TransactionDto entityToDto(Transaction entity){
        return new TransactionDto(entity.getId(), entity.getDescription(), entity.getAmount(), entity.getType(), entity.getCreated(), entity.getProfile(), entity.getCategory());
    }

    public static Transaction dtoToEntity(TransactionDto dto){
        return new Transaction(dto.getId(), dto.getDescription(), dto.getAmount(), dto.getType(), dto.getCreated(), dto.getProfile(), dto.getCategory());
    }

}
