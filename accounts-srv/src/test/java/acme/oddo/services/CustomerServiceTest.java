package acme.oddo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import acme.oddo.controllers.user.dto.CustomerDto;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService; 

    @Test 
    void saveCustomer() {
        CustomerDto customerDTO = new CustomerDto(); 
        customerDTO.setName("TEST");
        customerDTO.setSurname("SURNAMETEST");
        assertTrue(customerService.saveCustomer(customerDTO));
    }

    @Test 
    void saveCustomerKo() {
        CustomerDto customerDTO = new CustomerDto(); 
        customerDTO.setSurname("SURNAMETEST");
        assertFalse(customerService.saveCustomer(customerDTO));
    }

    @Test
    void isCustomerPresent() {
        CustomerDto customerDTO = new CustomerDto(); 
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

        CustomerDto customerDTO = new CustomerDto(); 
        customerDTO.setName("TEST");
        customerDTO.setSurname("SURNAMETEST");
        customerService.saveCustomer(customerDTO);
        assertEquals(customerDTO.getSurname(), customerService.getCustomer(3).getSurname());
    }

    @Test
    void getCustomerKo() {
        assertEquals(null, customerService.getCustomer(999).getName());
    }
}
