package acme.oddo.services;

import acme.oddo.controllers.user.dto.CustomerDto;

public interface CustomerService {
    
    public boolean isCustomerPresent(Integer customerID); 

    public CustomerDto getCustomer(Integer customerID); 

    public boolean saveCustomer(CustomerDto customerDTO); 
}
