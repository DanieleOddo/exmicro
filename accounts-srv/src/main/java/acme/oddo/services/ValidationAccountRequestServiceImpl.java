package acme.oddo.services;

import org.springframework.stereotype.Component;

import acme.oddo.controllers.account.dto.RequestAccountDTO;
import acme.oddo.utils.CheckValidInput;

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
