package acme.oddo.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import acme.oddo.data.entities.CustomerEntity;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {

    CustomerEntity findByCustomerID(Integer customerID);  
    
}