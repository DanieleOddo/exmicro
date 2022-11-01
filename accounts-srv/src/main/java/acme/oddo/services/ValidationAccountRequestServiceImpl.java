package acme.oddo.services;

import org.springframework.stereotype.Component;

import acme.oddo.controllers.account.dto.RequestAccountDTO;

@Component
public class ValidationAccountRequestServiceImpl implements ValidationAccountRequestService{

    @Override
    public boolean isValidAccountInput(RequestAccountDTO request) {
        if (request.getInitialCredit() == null) {
            return false; 
        }
        return true; 
    }    
}
