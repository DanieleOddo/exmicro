package acme.oddo.controllers.user.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class TransactionDto {

    @NonNull
    Integer customerID; 
    
    @NonNull
    Integer accountID;

    @NonNull
    LocalDate transactionDate;

    @NonNull
    Double impValue;

    
}
