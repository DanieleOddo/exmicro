package acme.oddo.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

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
    public void notValidInputForInsert () throws Exception {
        Throwable exception = assertThrows(NullPointerException.class, 
            () ->  {
                RequestTransactionDTO request = new RequestTransactionDTO();
                request.setAccountID(null);
                });

    }

    // TODO : Test for Insert Valid
    @Test
    public void insertValid() throws Exception {
        RequestTransactionDTO request = new RequestTransactionDTO();
        request.setAccountID(2);
        request.setImpValue(Double.parseDouble("10.10")); 

        assertThat(transactionService.createTransaction(request).equals(TransactionConst.INSERT_OK)); 

    }
}
