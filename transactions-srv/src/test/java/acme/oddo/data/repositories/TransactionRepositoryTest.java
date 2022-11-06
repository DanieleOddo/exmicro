package acme.oddo.data.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import acme.oddo.data.entities.TransactionEntity;

@DataJpaTest
public class TransactionRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    TransactionRepository repository;

    @Test
    public void shouldBeEmpty() {
        List<TransactionEntity> transactionList = (List<TransactionEntity>) repository.findAllByCustomerIDAndAccountID(21, 1); 
        assertThat(transactionList).isEmpty();
    }

    @Test
    public void itShouldSaveTransaction() {
        TransactionEntity transaction = new TransactionEntity();
        transaction.setAccountID(2);
        transaction.setAccountID(1);
        BigDecimal bdFromString = new BigDecimal("10.12");
        transaction.setImpValue(bdFromString);
        transaction = repository.save(transaction);
        assertThat(transaction).hasFieldOrPropertyWithValue("accountID", 1);            
    }   
    
    @Test
    public void itShoudBeSumOfTransaction() {
        TransactionEntity tran1 = new TransactionEntity();
        tran1.setCustomerID(11);
        tran1.setAccountID(2);
        tran1.setImpValue(new BigDecimal("10.12"));
        tran1 = repository.save(tran1);

        TransactionEntity tran2 = new TransactionEntity();
        tran2.setCustomerID(11);
        tran2.setAccountID(2);
        tran2.setImpValue(new BigDecimal("-05.11"));
        tran2 = repository.save(tran2);

        TransactionEntity tran3 = new TransactionEntity();
        tran3.setCustomerID(11);
        tran3.setAccountID(2);
        tran3.setImpValue(new BigDecimal("5.00"));
        tran3 = repository.save(tran3);

        BigDecimal balance = (BigDecimal) repository.findByCustomerIDAndAccountID(11, 2);
        BigDecimal testBalance = new BigDecimal("10.01");

        assertThat(balance).isEqualByComparingTo(testBalance);
    }

     @Test
     public void itShoudListOfTransaction() {
         TransactionEntity tran1 = new TransactionEntity();
         tran1.setCustomerID(12);
         tran1.setAccountID(2);
         tran1.setImpValue(new BigDecimal("10.12"));
         tran1 = repository.save(tran1);
 
         TransactionEntity tran2 = new TransactionEntity();
         tran2.setCustomerID(12);
         tran2.setAccountID(2);
         tran2.setImpValue(new BigDecimal("-05.11"));
         tran2 = repository.save(tran2);
 
         TransactionEntity tran3 = new TransactionEntity();
         tran3.setCustomerID(12);
         tran3.setAccountID(2);
         tran3.setImpValue(new BigDecimal("5.00"));
         tran3 = repository.save(tran3);
 
         List<TransactionEntity> listTransaction = repository.findAllByCustomerIDAndAccountID(12, 2);
         
         assertThat(listTransaction).hasSize(3).contains(tran1, tran2, tran3);
     }
}
