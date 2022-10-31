package acme.oddo.services;

import acme.oddo.controllers.transaction.dto.RequestTransactionDTO;

public interface TransactionService {

    public String createTransaction(RequestTransactionDTO request);
    
}
