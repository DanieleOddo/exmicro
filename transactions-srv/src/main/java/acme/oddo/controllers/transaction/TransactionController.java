package acme.oddo.controllers.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import acme.oddo.controllers.transaction.dto.RequestTransactionDTO;
import acme.oddo.services.TransactionService;
import acme.oddo.utils.TransactionConst;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PutMapping("/newTransaction")
    @ResponseBody
    public ResponseEntity newTransaction (RequestTransactionDTO request) {
            String result = transactionService.createTransaction(request);
            if (result.equals(TransactionConst.INSERT_OK)) {
                return ResponseEntity.ok(HttpStatus.OK);
            }
            return new ResponseEntity<>(TransactionConst.INSERT_KO, HttpStatus.BAD_REQUEST);
        }
    }
    

