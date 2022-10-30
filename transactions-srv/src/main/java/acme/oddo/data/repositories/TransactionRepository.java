package acme.oddo.data.repositories;

import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import acme.oddo.data.entities.TransactionEntity;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer{
    
}
