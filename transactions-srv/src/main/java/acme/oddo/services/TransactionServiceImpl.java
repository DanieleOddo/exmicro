package acme.oddo.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.oddo.controllers.transaction.dto.RequestTransactionDTO;
import acme.oddo.data.entities.TransactionEntity;
import acme.oddo.data.repositories.TransactionRepository;
import acme.oddo.utils.TransactionConst;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    TransactionRepository repository;

    @Override
    public String createTransaction(RequestTransactionDTO request) {
        TransactionEntity entity = new TransactionEntity(); 
        try {
            entity.setAccountID(request.getAccountID());
            entity.setImpValue(BigDecimal.valueOf(request.getImpValue()));
            entity = repository.save(entity);
            return TransactionConst.INSERT_OK;  
        } 
        catch (Exception e) {
            log.error(TransactionConst.GENERIC_ERR, e);
            return TransactionConst.INSERT_KO;  
        }
    }
    
}
