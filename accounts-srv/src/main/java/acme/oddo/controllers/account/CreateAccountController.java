package acme.oddo.controllers.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import acme.oddo.controllers.account.dto.RequestAccountDTO;
import acme.oddo.services.AccountService;
import acme.oddo.services.CustomerService;
import acme.oddo.services.ValidationAccountRequestService;
import acme.oddo.utils.AccountConst;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CreateAccountController {

	@Autowired
	ValidationAccountRequestService validService; 

	@Autowired
	CustomerService customerService;

	@Autowired
	AccountService accountService;

    @PostMapping("/newAccount")
	@ResponseBody
	public ResponseEntity<String> newAccount(String input) {
		try {
			ObjectMapper mapper = new ObjectMapper(); 
			RequestAccountDTO reqs = mapper.readValue(input, RequestAccountDTO.class);
			if (!validService.isValidAccountInput(reqs)) {
				return new ResponseEntity<>(AccountConst.NOTVALIDMSG, HttpStatus.BAD_REQUEST);
			} 
			if (customerService.isCustomerPresent(reqs.getCustomerID())) {
				return new ResponseEntity<>(AccountConst.NOTCUSTOMERPRESENT, HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<>("Daniele OK", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("msg:", e.toString());
			return new ResponseEntity<>("Daniele 2 KO", HttpStatus.BAD_REQUEST);
		}
	}
    
	
}
