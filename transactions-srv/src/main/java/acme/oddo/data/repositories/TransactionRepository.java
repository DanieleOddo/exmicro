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


    @Query(value = "SELECT sum(impValue) FROM Transaction WHERE accountID = :accountID", nativeQuery = true)
    public BigDecimal getBalanceValue(@Param("accountID") Integer accountID);

    public List<TransactionEntity> findAllByAccountID(Integer accountID);    
    
}
