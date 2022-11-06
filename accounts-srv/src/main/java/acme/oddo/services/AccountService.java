package acme.oddo.services;

import java.util.List;

import acme.oddo.services.dto.ResponseAccountDto;

public interface AccountService {

    public ResponseAccountDto createAccount(Integer customerID, Double initialCredit); 

    public boolean isAccountPresent(Integer customerID, Integer accountID); 

    List<Integer> listAccountByCustomer(Integer customerID); 

    
    
}
