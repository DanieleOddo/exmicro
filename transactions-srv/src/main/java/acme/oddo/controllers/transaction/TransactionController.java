package acme.oddo.controllers.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import acme.oddo.controllers.transaction.dto.RequestTransactionDTO;
import acme.oddo.controllers.transaction.dto.ResponseTransactionDto;
import acme.oddo.services.TransactionService;
import acme.oddo.utils.TransactionConst;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/V1/newTransaction")
    public ResponseEntity<ResponseTransactionDto> createProduct(@RequestBody RequestTransactionDTO input){
        try {
            ResponseTransactionDto result = transactionService.createTransaction(input);
            return ResponseEntity.ok().body(result); 
        } catch (Exception e) {
            log.error(TransactionConst.ERRNSG, e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }
}    

