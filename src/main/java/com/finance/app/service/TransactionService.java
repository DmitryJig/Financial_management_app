package com.finance.app.service;

import com.finance.app.exception.ResourceNotFoundException;
import com.finance.app.model.entity.Transaction;
import com.finance.app.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private TransactionRepository transactionRepository;

    public List<Transaction> findAll() {return transactionRepository.findAll();}

    public void delete(Transaction transaction) {transactionRepository.delete(transaction);}

    public void save(Transaction transaction) {transactionRepository.save(transaction);}

    public Transaction findById(Long id){
        return transactionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format("Transaction with id = %d not found", id)));
    }
}
