package acme.oddo.controllers.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

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
    public ResponseEntity<String> newTransaction (String input) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            RequestTransactionDTO request = mapper.readValue(input, RequestTransactionDTO.class);
            transactionService.createTransaction(request);
            return new ResponseEntity<>(TransactionConst.INSERT_OK, HttpStatus.OK);
        } catch (Exception e) {
            log.error(TransactionConst.ERRNSG, e.toString());
            return new ResponseEntity<>(TransactionConst.INSERT_KO, HttpStatus.BAD_REQUEST);
        }    
    }
}    

