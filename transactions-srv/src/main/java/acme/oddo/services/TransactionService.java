package acme.oddo.services;

import java.util.List;

import acme.oddo.controllers.reports.dto.TransactionDto;
import acme.oddo.controllers.transaction.dto.RequestTransactionDTO;
import acme.oddo.controllers.transaction.dto.ResponseTransactionDto;

public interface TransactionService {

    public ResponseTransactionDto createTransaction(RequestTransactionDTO request);

    public Double getBalanceByCustomerAndAccount(Integer customerID, Integer accountID);

    public List<TransactionDto> getAllTransactionByCustomerAndAccount(Integer customerID, Integer accountID);
    
}
