package acme.oddo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import acme.oddo.controllers.user.dto.CustomerDto;
import acme.oddo.data.entities.CustomerEntity;
import acme.oddo.data.repositories.CustomerRepository;
import acme.oddo.utils.AccountConst;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository repository;

    @Override
    public boolean isCustomerPresent(Integer customerID) {
        try {
            CustomerEntity customer = repository.findByCustomerID(customerID);
            if (customer != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error(AccountConst.ERRMSG, e.getMessage());   
            return false;
        }
        
    }

    @Override
    public CustomerDto getCustomer(Integer customerID) {
        try {
            CustomerEntity customer = repository.findByCustomerID(customerID);
            CustomerDto customerResponse = new CustomerDto();
            if (customer != null) {
                customerResponse.setCustomerID(customerID);
                customerResponse.setName(customer.getName());
                customerResponse.setSurname(customer.getSurname());
                return customerResponse;
            }    
            return customerResponse; 
        } catch (Exception e) {
            log.error(AccountConst.ERRMSG, e.getMessage());   
            return null;
        }
    }

    @Override
    public boolean saveCustomer(CustomerDto customerDTO) {
        try {
            CustomerEntity customer = new CustomerEntity();
            if ((customerDTO.getName() != null) && (customerDTO.getSurname() != null)) {
                customer.setName(customerDTO.getName());
                customer.setSurname(customerDTO.getSurname());
                repository.save(customer);
                return true;
            }  
            return false;     
        } catch (Exception e) {
            log.error(AccountConst.ERRMSG, e.getMessage());   
            return false;
        }
    }
    
}
