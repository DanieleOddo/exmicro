package acme.oddo.controllers.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import acme.oddo.controllers.user.dto.ResponseUserDTO;
import acme.oddo.utils.AccountConst;
import acme.oddo.utils.CheckValidInput;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {
    
    @GetMapping("/userInfo")
    @ResponseBody
    public ResponseEntity<ResponseUserDTO> getUserInfo(String account) {
        try {
            if (CheckValidInput.isStringNumeric(account)) {
                return new ResponseEntity<>(HttpStatus.OK);    
            }    
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(AccountConst.ERRMSG, e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        
    }
}
