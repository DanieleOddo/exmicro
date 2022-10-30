package acme.oddo.controllers.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import acme.oddo.controllers.transaction.dto.RequestTransactionDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TransactionController {

    @PutMapping("/newTransaction")
    @ResponseBody
    public ResponseEntity newTransaction (RequestTransactionDTO request) {
        try {
            
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }


       
    }
    
    
}
