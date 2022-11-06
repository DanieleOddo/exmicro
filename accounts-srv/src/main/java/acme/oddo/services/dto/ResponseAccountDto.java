package acme.oddo.services.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseAccountDto implements Serializable {
    
    private Integer accountID;
    private Integer customerID;
    private LocalDate transactionDate;
    private Double  initialCredit;
}

