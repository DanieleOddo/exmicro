package acme.oddo.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import acme.oddo.controllers.reports.dto.TransactionDto;
import acme.oddo.controllers.transaction.dto.RequestTransactionDTO;
import acme.oddo.data.entities.TransactionEntity;
import acme.oddo.data.repositories.TransactionRepository;
import acme.oddo.utils.TransactionConst;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    TransactionRepository repository;

    @Override
    public String createTransaction(RequestTransactionDTO request) {
        TransactionEntity entity = new TransactionEntity(); 
        try {
            entity.setAccountID(request.getAccountID());
            entity.setImpValue(BigDecimal.valueOf(request.getImpValue()));
            repository.save(entity);
            return TransactionConst.INSERT_OK;  
        } 
        catch (Exception e) {
            log.error(TransactionConst.GENERIC_ERR, e.toString());
            return TransactionConst.INSERT_KO;  
        }
    }

    @Override
    public Double getBalanceByAccount(Integer accountID) {
        try {
            BigDecimal balance = repository.findByAccountID(accountID);
            return balance.doubleValue();
        } catch (Exception e) {
            log.error(TransactionConst.GENERIC_ERR, e.toString());
            return Double.NaN;
        }
    }

    @Override
    public List<TransactionDto> getAllTransactionByAccount(Integer accountID) {
        List<TransactionDto> resultList = new ArrayList<>();  
        try {
            List<TransactionEntity> lEntities = repository.findAllByAccountID(accountID);
            for (TransactionEntity transactionEntity : lEntities) {
                TransactionDto transactionDto = new TransactionDto();
                transactionDto.setTransactionDate(transactionEntity.getCreateOn().toLocalDate());
                transactionDto.setImpValue(transactionEntity.getImpValue().doubleValue());
                resultList.add(transactionDto); 
            }
            return resultList; 
        } catch (Exception e) {
            log.error(TransactionConst.GENERIC_ERR, e.toString());
            return resultList; 
        }
    }
    
}
