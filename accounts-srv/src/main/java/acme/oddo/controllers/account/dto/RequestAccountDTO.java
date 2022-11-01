package acme.oddo.controllers.account.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class RequestAccountDTO implements Serializable {
    
    @NonNull
    private Integer customerID;
    private String name;
    private String surname;
    private Double initialCredit; 
}
