package acme.oddo.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import acme.oddo.data.entities.AccountEntity;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {
    
}
