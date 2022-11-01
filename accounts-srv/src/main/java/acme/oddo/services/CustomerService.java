package acme.oddo.services;

import acme.oddo.controllers.user.CustomerDTO;

public interface CustomerService {
    
    public boolean isCustomerPresent(Integer customerID); 

    public CustomerDTO getCustomer(Integer customerID); 

    public boolean saveCustomer(CustomerDTO customerDTO); 
}
