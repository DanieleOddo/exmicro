package acme.oddo.controllers.user.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionSimpleDto {
    
    private LocalDate transactionDate;
    private Double impValue; 
}
