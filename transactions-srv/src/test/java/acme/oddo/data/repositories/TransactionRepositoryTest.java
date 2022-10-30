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
        List<TransactionEntity> transactionList = (List<TransactionEntity>) repository.findAll(); 
        assertThat(transactionList).isEmpty();
    }

    @Test
    public void itShouldSaveTransaction() {
        TransactionEntity transaction = new TransactionEntity();
        transaction.setAccountID(1);
        BigDecimal bdFromString = new BigDecimal("10.12");
        transaction.setImpValue(bdFromString);
        transaction = repository.save(transaction);
        assertThat(transaction).hasFieldOrPropertyWithValue("accountID", 1);            
    }    
}
