package acme.oddo.controllers.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    @GetMapping("/userInfo")
    public ResponseUserDTO getUserInfo(String account) {
        return null;
    }
}
