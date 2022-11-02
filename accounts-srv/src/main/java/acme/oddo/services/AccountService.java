package acme.oddo.services;

import acme.oddo.controllers.account.dto.RequestAccountDTO;

public interface AccountService {

    public boolean createAccount(RequestAccountDTO request); 
    
}
