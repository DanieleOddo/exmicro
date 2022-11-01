package acme.oddo.services;

import java.util.List;

import acme.oddo.controllers.reports.dto.TransactionDto;
import acme.oddo.controllers.transaction.dto.RequestTransactionDTO;

public interface TransactionService {

    public String createTransaction(RequestTransactionDTO request);

    public Double getBalanceByAccount(Integer accountID);

    public List<TransactionDto> getAllTransactionByAccount(Integer accountID);
    
}
