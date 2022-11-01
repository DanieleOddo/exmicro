package acme.oddo.controllers.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import acme.oddo.controllers.account.dto.RequestAccountDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CreateAccountController {

    @PostMapping("/newAccount")
	@ResponseBody
	public ResponseEntity<String> newAccount(String input) {
		try {
			ObjectMapper mapper = new ObjectMapper(); 
			RequestAccountDTO reqs = mapper.readValue(input, RequestAccountDTO.class); 
			return new ResponseEntity<>("Daniele KO", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("msg:", e.toString());
			return new ResponseEntity<>("Daniele 2 KO", HttpStatus.BAD_REQUEST);
		}
	}
    
}
