package acme.oddo.controllers.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CreateAccountController {

    
    @GetMapping("/")
	public @ResponseBody String greeting() {
		log.info("Ciao Daniele");
		return "Hello, World";
	}
    
}
