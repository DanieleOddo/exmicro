package acme.oddo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import acme.oddo.controllers.user.dto.CustomerDTO;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService; 

    @Test 
    void saveCustomer() {
        CustomerDTO customerDTO = new CustomerDTO(); 
        customerDTO.setName("TEST");
        customerDTO.setSurname("SURNAMETEST");
        assertTrue(customerService.saveCustomer(customerDTO));
    }

    @Test 
    void saveCustomerKo() {
        CustomerDTO customerDTO = new CustomerDTO(); 
        customerDTO.setSurname("SURNAMETEST");
        assertFalse(customerService.saveCustomer(customerDTO));
    }

    @Test
    void isCustomerPresent() {
        CustomerDTO customerDTO = new CustomerDTO(); 
        customerDTO.setName("TEST");
        customerDTO.setSurname("SURNAMETEST");
        customerService.saveCustomer(customerDTO);
        assertTrue(customerService.isCustomerPresent(3));
    }

    @Test
    void isCustomerAbsent() { 
        assertFalse(customerService.isCustomerPresent(9999));
    }
    
    @Test
    void getCustomer() {

        CustomerDTO customerDTO = new CustomerDTO(); 
        customerDTO.setName("TEST");
        customerDTO.setSurname("SURNAMETEST");
        customerService.saveCustomer(customerDTO);
        assertEquals(customerDTO.getSurname(), customerService.getCustomer(3).getSurname());
    }

    @Test
    void getCustomerKo() {
        assertEquals(null, customerService.getCustomer(999));
    }
}
