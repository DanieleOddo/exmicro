package acme.oddo.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    AccountService accountService; 

    @Test
    void findAccount() {
        assertTrue(accountService.isAccountPresent(2, 2));
    }

    @Test
    void notFindAccount() {
        assertFalse(accountService.isAccountPresent(10, 10));
    }

    @Test
    void listAccount() {
        List<Integer> listAccount = accountService.listAccountByCustomer(2);
        assertFalse(listAccount.isEmpty());
    }

    @Test
    void listEmpty() {
        List<Integer> listAccount = accountService.listAccountByCustomer(99);
        assertTrue(listAccount.isEmpty());
    }
 
    
}
