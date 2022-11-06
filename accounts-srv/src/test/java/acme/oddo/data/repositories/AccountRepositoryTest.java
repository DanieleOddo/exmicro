package acme.oddo.data.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import acme.oddo.data.entities.AccountEntity;
import acme.oddo.data.entities.CustomerEntity;

@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    AccountRepository accountRepository; 

    @Autowired
    CustomerRepository customerRepository;  

    @Test
    void itSchouldBeSave() {
        CustomerEntity customer = customerRepository.findByCustomerID(1);
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setCustomer(customer);
        accountRepository.save(accountEntity); 
        assertEquals(customer, accountRepository.findByAccountIDAndCustomer_CustomerID(accountEntity.getAccountID(), 1).getCustomer());
    }

    @Test
    void itSchouldNotFound() {
        assertEquals(null,  accountRepository.findByAccountIDAndCustomer_CustomerID(99,99));
    }
 
    @Test
    void itSchouldBeFound() {
        assertNotNull(accountRepository.findByAccountIDAndCustomer_CustomerID(1,2));
    }

    @Test
    void listAccount() {
        List<AccountEntity> listAccount = accountRepository.findAllByCustomer_CustomerID(2);
        assertFalse(listAccount.isEmpty());
    }

    @Test
    void listEmpty() {
        List<AccountEntity> listAccount = accountRepository.findAllByCustomer_CustomerID(99);
        assertTrue(listAccount.isEmpty());
    }
 
}
