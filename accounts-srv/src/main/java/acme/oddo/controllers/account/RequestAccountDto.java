package acme.oddo.controllers.account;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestAccountDto {
    
    public Integer customerID;
    public String initialCredit; 
}
