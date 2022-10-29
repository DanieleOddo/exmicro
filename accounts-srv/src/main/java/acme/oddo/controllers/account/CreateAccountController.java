package acme.oddo.controllers.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateAccountController {

    
    @GetMapping("/")
	public @ResponseBody String greeting() {
		return "Hello, World";
	}
    
}
