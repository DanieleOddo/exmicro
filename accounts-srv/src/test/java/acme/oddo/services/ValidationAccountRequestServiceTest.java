package acme.oddo.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import acme.oddo.controllers.account.dto.RequestAccountDTO;

@SpringBootTest
public class ValidationAccountRequestServiceTest {
    
    @Autowired
    ValidationAccountRequestService service; 

    @Test
    public void initialCreditNull() throws Exception {
        RequestAccountDTO request = new RequestAccountDTO();
        assertTrue(!service.isValidAccountInput(request));
    }
    
    @Test
    public void initialCreditValid() throws Exception {
        RequestAccountDTO request = new RequestAccountDTO();
        request.setCustomerID(7);
        request.setInitialCredit(Double.parseDouble("10.00"));
        assertTrue(service.isValidAccountInput(request));
    }
}
