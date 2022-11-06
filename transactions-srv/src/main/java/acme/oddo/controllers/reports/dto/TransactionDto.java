package acme.oddo.controllers.reports.dto;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class TransactionDto implements Serializable {

    @NonNull
    Integer customerID; 
    
    @NonNull
    Integer accountID;

    @NonNull
    LocalDate transactionDate;

    @NonNull
    Double impValue;

}
