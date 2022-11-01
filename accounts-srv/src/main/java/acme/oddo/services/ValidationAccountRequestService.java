package acme.oddo.services;

import acme.oddo.controllers.account.dto.RequestAccountDTO;

public interface ValidationAccountRequestService {

    public boolean isValidAccountInput(RequestAccountDTO request); 
}
