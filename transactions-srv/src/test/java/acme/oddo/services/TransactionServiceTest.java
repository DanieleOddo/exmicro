package acme.oddo.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.OptionalDouble;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import acme.oddo.controllers.transaction.dto.RequestTransactionDTO;
import acme.oddo.utils.TransactionConst;

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
        request.setAccountID(2);
        request.setImpValue(Double.parseDouble("10.10")); 
        assertThat(transactionService.createTransaction(request)).contains(TransactionConst.INSERT_OK); 
    }

    @Test
    public void getBalanceByAccount() throws Exception {
        RequestTransactionDTO re1 = new RequestTransactionDTO();
        re1.setAccountID(3);
        re1.setImpValue(Double.parseDouble("10.10")); 
        transactionService.createTransaction(re1);
        
        RequestTransactionDTO re2= new RequestTransactionDTO();
        re2.setAccountID(3);
        re2.setImpValue(Double.parseDouble("5.10")); 
        transactionService.createTransaction(re2);
        
        Double testBalance = Double.parseDouble("15.20");

        assertThat(transactionService.getBalanceByAccount(3)).isEqualTo(testBalance); 

    }

    @Test
    public void itBalanceEmpty() throws Exception {
        OptionalDouble value = OptionalDouble.of(transactionService.getBalanceByAccount(4));
        assertThat(!value.isPresent()).isFalse(); 
    }

    @Test
    void emptyList() {
        transactionService.getAllTransactionByAccount(3);
        assertTrue( transactionService.getAllTransactionByAccount(3).isEmpty());
    }

    @Test
    void listOfTransactionByAccount() {
        RequestTransactionDTO requestTransactionDTO = new RequestTransactionDTO();
        requestTransactionDTO.setAccountID(1);
        requestTransactionDTO.setImpValue(Double.parseDouble("9.00"));
        transactionService.createTransaction(requestTransactionDTO);
        assertTrue(!transactionService.getAllTransactionByAccount(1).isEmpty());
    }

}
