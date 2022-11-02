package acme.oddo.controllers.user.dto;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDTO implements Serializable {
    
    private Integer customerID;
    private String name;
    private String surname;
}
