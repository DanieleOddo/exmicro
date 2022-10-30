package acme.oddo.data.repositories;

import java.math.BigDecimal;

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
    public void itShouldSaveTransaction() {
        TransactionEntity transaction = new TransactionEntity();
        transaction.setAccountID(1);
        BigDecimal bdFromString = new BigDecimal("10.12");
        BigDecimal bdFromStringZero = new BigDecimal("0");
        transaction.setDepositValue(bdFromString);
        transaction.setInvoiceValue(bdFromStringZero);

    }
    
}
