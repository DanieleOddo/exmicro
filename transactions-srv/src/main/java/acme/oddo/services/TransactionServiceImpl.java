package acme.oddo.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import acme.oddo.controllers.reports.dto.TransactionDto;
import acme.oddo.controllers.transaction.dto.RequestTransactionDTO;
import acme.oddo.controllers.transaction.dto.ResponseTransactionDto;
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
    public ResponseTransactionDto createTransaction(RequestTransactionDTO request) {
        ResponseTransactionDto dto = new ResponseTransactionDto(); 
        try {
            TransactionEntity transactionEntity = new TransactionEntity(); 
            transactionEntity.setCustomerID(request.getCustomerID());
            transactionEntity.setAccountID(request.getAccountID());
            transactionEntity.setImpValue(BigDecimal.valueOf(request.getImpValue()));
            transactionEntity = repository.save(transactionEntity);
            dto.setCustomerID(transactionEntity.getCustomerID());
            dto.setAccountID(transactionEntity.getAccountID());
            dto.setImpValue(transactionEntity.getImpValue().doubleValue());
            dto.setTransactionDate(transactionEntity.getCreateOn().toLocalDate());
            return dto;  
        } 
        catch (Exception e) {
            log.error(TransactionConst.GENERIC_ERR_1, e.getMessage(), e.getCause());
            return dto;  
        }
    }

    @Override
    public Double getBalanceByCustomerAndAccount(Integer customerID, Integer accountID) {
        try {
            BigDecimal balance = repository.findByCustomerIDAndAccountID(customerID, accountID);
            return balance.doubleValue();
        } catch (Exception e) {
            log.error(TransactionConst.GENERIC_ERR_1, e.getMessage(), e.getCause());
            return Double.NaN;
        }
    }

    @Override
    public List<TransactionDto> getAllTransactionByCustomerAndAccount(Integer customerID, Integer accountID) {
        List<TransactionDto> resultList = new ArrayList<>();  
        try {
            List<TransactionEntity> lEntities = repository.findAllByCustomerIDAndAccountID(customerID, accountID);
            for (TransactionEntity transactionEntity : lEntities) {
                TransactionDto transactionDto = new TransactionDto();
                transactionDto.setAccountID(transactionEntity.getAccountID());
                transactionDto.setCustomerID(transactionEntity.getCustomerID());
                transactionDto.setTransactionDate(transactionEntity.getCreateOn().toLocalDate());
                transactionDto.setImpValue(transactionEntity.getImpValue().doubleValue());
                resultList.add(transactionDto); 
            }
            return resultList; 
        } catch (Exception e) {
            log.error(TransactionConst.GENERIC_ERR_1, e.getMessage(), e.getCause());
            return resultList; 
        }
    }
    
}
