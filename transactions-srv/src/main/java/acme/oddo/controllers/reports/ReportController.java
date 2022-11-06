package acme.oddo.controllers.reports;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import acme.oddo.controllers.reports.dto.ResponseReportDTO;
import acme.oddo.controllers.reports.dto.TransactionDto;
import acme.oddo.services.TransactionService;
import acme.oddo.utils.TransactionConst;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ReportController {
    @Autowired
    TransactionService transactionService;

    @GetMapping("/V1/infoAccount/customer/{customerID}/account/{accountID}")
    public ResponseEntity<ResponseReportDTO> getReportByAccount(@PathVariable Integer customerID, @PathVariable Integer accountID) {
        ResponseReportDTO respo = new ResponseReportDTO();
        try {
            List<TransactionDto> listTransaction = getTransactionByCustomerAndAccount(customerID, accountID);
            if (listTransaction.isEmpty()) {
                return ResponseEntity.badRequest().body(null); 
            }
            respo.setLDtos(listTransaction);
            Double value = getBalance(customerID, accountID);
            if (!value.isNaN()) {
                respo.setBalance(value);
            } else {
                respo.setBalance(Double.valueOf(0));
            }
            return ResponseEntity.ok().body(respo);
        } catch (Exception e) {
            log.error(TransactionConst.ERRNSG, e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }


    private Double getBalance(Integer customer, Integer account) {
        try {
            return transactionService.getBalanceByCustomerAndAccount(customer, account);
        } catch (Exception e) {
            log.error(TransactionConst.ERRNSG, e.getMessage());
            return Double.NaN;
        }
    }

    private List<TransactionDto> getTransactionByCustomerAndAccount(Integer customer, Integer account) {
        try {
            return transactionService.getAllTransactionByCustomerAndAccount(customer, account);
        } catch (Exception e) {
            log.error(TransactionConst.ERRNSG, e.getMessage());
            return new ArrayList<>();
        }
    }
}
