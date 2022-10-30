package acme.oddo.controllers.transaction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestTransactionDTO {
    
    private Integer accountID;
    private Double impValue; 
}
