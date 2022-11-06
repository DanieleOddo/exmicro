package acme.oddo.data.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import acme.oddo.data.entities.AccountEntity;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Integer> {
    
    public AccountEntity findByAccountIDAndCustomer_CustomerID(Integer accountID, Integer customerID);

    public List<AccountEntity> findAllByCustomer_CustomerID(Integer customerID);
    
}
