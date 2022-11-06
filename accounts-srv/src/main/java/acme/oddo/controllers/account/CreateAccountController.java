package acme.oddo.controllers.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import acme.oddo.services.AccountService;
import acme.oddo.services.CustomerService;
import acme.oddo.services.dto.ResponseAccountDto;
import acme.oddo.utils.CheckValidInput;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CreateAccountController {

	@Autowired
	CustomerService customerService;

	@Autowired
	AccountService accountService;

    // @PostMapping("/V1/newAccount/")
	// public ResponseEntity<ResponseAccountDto> newAccount(@RequestParam(name = "customerID") Integer customerID, @RequestParam(name = "initialCredit") String initialCredit) {
		 @PostMapping("/V1/newAccount/customer/{customerID}/initCredit/{initialCredit}")
		 public ResponseEntity<ResponseAccountDto> newAccount(@PathVariable Integer customerID, @PathVariable String initialCredit) {

				ResponseAccountDto response = new ResponseAccountDto();
		try {
			if (!customerService.isCustomerPresent(customerID)) {
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			if (!CheckValidInput.isStringNumeric(initialCredit)) {	
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			response = accountService.createAccount(customerID, Double.valueOf(initialCredit)); 
			if (response != null) {
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.error("msg:", e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
