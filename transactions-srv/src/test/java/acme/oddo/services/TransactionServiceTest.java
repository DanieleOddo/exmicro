package acme.oddo.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.OptionalDouble;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import acme.oddo.controllers.transaction.dto.RequestTransactionDTO;

@SpringBootTest
public class TransactionServiceTest {
    
    @Autowired
    TransactionService transactionService; 

    @Test 
    public void notValidInputForInsert () {
        RequestTransactionDTO request = new RequestTransactionDTO();
        try {
            request.setAccountID(null);    
        } catch (NullPointerException ex) {
            assertTrue(ex instanceof NullPointerException);
        }
    }

    @Test 
    public void notValidInput () {
        RequestTransactionDTO testNotValid = new RequestTransactionDTO();
        try {
            transactionService.createTransaction(testNotValid);
        } catch (NullPointerException ex) {
            assertTrue(ex instanceof NullPointerException);
        }
    }
     

    @Test
    public void insertValid() throws Exception {
        RequestTransactionDTO request = new RequestTransactionDTO();
        request.setCustomerID(22);
        request.setAccountID(2);
        request.setImpValue(Double.parseDouble("10.10")); 
        assertNotNull(transactionService.createTransaction(request)); 
    }

    @Test
    public void getBalanceByCustomerAndAccount() throws Exception {
        RequestTransactionDTO re1 = new RequestTransactionDTO();
        re1.setCustomerID(32);
        re1.setAccountID(3);
        re1.setImpValue(Double.parseDouble("10.10")); 
        transactionService.createTransaction(re1);
        
        RequestTransactionDTO re2= new RequestTransactionDTO();
        re2.setCustomerID(32);
        re2.setAccountID(3);
        re2.setImpValue(Double.parseDouble("5.10")); 
        transactionService.createTransaction(re2);
        
        Double testBalance = Double.parseDouble("15.20");

        assertThat(transactionService.getBalanceByCustomerAndAccount(32, 3)).isEqualTo(testBalance); 

    }

    @Test
    public void itBalanceEmpty() throws Exception {
        OptionalDouble value = OptionalDouble.of(transactionService.getBalanceByCustomerAndAccount(24, 4));
        assertThat(!value.isPresent()).isFalse(); 
    }

    @Test
    void emptyList() {
        assertTrue( transactionService.getAllTransactionByCustomerAndAccount(25, 3).isEmpty());
    }

    @Test
    void listOfTransactionByCustomerAndAccount() {
        RequestTransactionDTO requestTransactionDTO = new RequestTransactionDTO();
        requestTransactionDTO.setCustomerID(35);
        requestTransactionDTO.setAccountID(1);
        requestTransactionDTO.setImpValue(Double.parseDouble("9.00"));
        transactionService.createTransaction(requestTransactionDTO);
        assertFalse(transactionService.getAllTransactionByCustomerAndAccount(35, 1).isEmpty());
    }

}
