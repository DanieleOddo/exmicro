package acme.oddo.controllers.transaction.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class ResponseTransactionDto {
    
    @NonNull
    Integer customerID; 
    
    @NonNull
    Integer accountID;

    @NonNull
    LocalDate transactionDate;

    @NonNull
    Double impValue;
}
