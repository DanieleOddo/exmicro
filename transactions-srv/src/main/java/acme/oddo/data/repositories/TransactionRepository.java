package acme.oddo.data.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.oddo.data.entities.TransactionEntity;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer>{


    //  I'm going to use a Native query!
    @Query(value = "SELECT SUM(IMP_VALUE) FROM Transaction WHERE ACCOUNT_ID = :accountID", nativeQuery = true)
    public BigDecimal findByAccountID(@Param ("accountID") Integer accountID);

    public List<TransactionEntity> findAllByAccountID(Integer accountID);    
    
}
