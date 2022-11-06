package acme.oddo.controllers.transaction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class RequestTransactionDTO {
    
    @NonNull
    private Integer customerID;
    @NonNull
    private Integer accountID;
    @NonNull
    private Double impValue; 
}
