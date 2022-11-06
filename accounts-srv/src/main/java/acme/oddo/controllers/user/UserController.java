package acme.oddo.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import acme.oddo.controllers.user.dto.AccountsUserInfoDto;
import acme.oddo.controllers.user.dto.UserInfoDto;
import acme.oddo.services.AccountService;
import acme.oddo.services.CustomerService;
import acme.oddo.services.TransactionReportService;
import acme.oddo.utils.AccountConst;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {
    
    @Autowired
    TransactionReportService reportService; 

    @Autowired
    CustomerService customerService; 

    @Autowired
    AccountService accountService;

    @GetMapping("/V1/customeraccountInfo/customer/{customerID}/account/{accountID}")
    public ResponseEntity<UserInfoDto> getUserInfo(@PathVariable Integer customerID, @PathVariable Integer accountID) {
        try {
            if (!customerService.isCustomerPresent(customerID)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!accountService.isAccountPresent(customerID, accountID)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            UserInfoDto response = reportService.createReport(customerID, accountID);  
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            log.error(AccountConst.ERRMSG, e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/V1/customerInfo/customer/{customerID}")
    public ResponseEntity<AccountsUserInfoDto> listAccountUserInfo(@PathVariable Integer customerID) {
        try {
            if (!customerService.isCustomerPresent(customerID)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if ((accountService.listAccountByCustomer(customerID) == null) || (accountService.listAccountByCustomer(customerID).isEmpty())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            AccountsUserInfoDto response = reportService.getTransactionAndAccountsForCustomer(customerID);  
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            log.error(AccountConst.ERRMSG, e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
