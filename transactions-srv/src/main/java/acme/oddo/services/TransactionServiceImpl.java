package acme.oddo.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.oddo.controllers.transaction.dto.RequestTransactionDTO;
import acme.oddo.data.entities.TransactionEntity;
import acme.oddo.data.repositories.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    TransactionRepository repository;

    @Override
    public void createTransaction(RequestTransactionDTO request) {
        // TODO Auto-generated method stub
        try {
            TransactionEntity entity = new TransactionEntity(); 
            entity.setAccountID(request.getAccountID());
            entity.setImpValue(BigDecimal.valueOf(request.getImpValue()));
            entity = repository.save(entity); 
        } 
        catch (Exception e) {
            // TODO: handle exception
        }
    }
    
}
