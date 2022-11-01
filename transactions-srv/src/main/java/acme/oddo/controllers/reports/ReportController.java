package acme.oddo.controllers.reports;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import acme.oddo.services.TransactionService;
import acme.oddo.utils.TransactionConst;
import lombok.extern.slf4j.Slf4j;
import acme.oddo.controllers.reports.dto.ResponseReportDTO;
import acme.oddo.controllers.reports.dto.TransactionDto;

@Slf4j
@RestController
public class ReportController {
    @Autowired
    TransactionService transactionService;

    @GetMapping("/reportAccount")
    @ResponseBody
    public ResponseEntity<ResponseReportDTO> getReportByAccount(@RequestParam String account) {
        ResponseReportDTO respo = new ResponseReportDTO();
        try {
            List<TransactionDto> listTransaction = getTransactionByAccount(Integer.valueOf(account));
            if (listTransaction.isEmpty()) {
                return new ResponseEntity<>(respo, HttpStatus.BAD_REQUEST);    
            }
            respo.setLDtos(listTransaction);
            respo.setBalance(getBalance(Integer.valueOf(account)));
            return new ResponseEntity<>(respo, HttpStatus.OK);
        } catch (Exception e) {
            log.error(TransactionConst.ERRNSG, e.toString());
            return new ResponseEntity<>(respo, HttpStatus.BAD_REQUEST);
        }
    }


    private Double getBalance(Integer account) {
        try {
            return transactionService.getBalanceByAccount(account);
        } catch (Exception e) {
            log.error(TransactionConst.ERRNSG, e.toString());
            return Double.NaN;
        }
    }

    private List<TransactionDto> getTransactionByAccount(Integer account) {
        try {
            return transactionService.getAllTransactionByAccount(account);
        } catch (Exception e) {
            log.error(TransactionConst.ERRNSG, e.toString());
            return new ArrayList<>();
        }
    }
}
