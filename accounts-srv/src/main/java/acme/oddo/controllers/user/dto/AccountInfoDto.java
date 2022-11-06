package acme.oddo.controllers.user.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountInfoDto {

    private Integer accountID;
    private Double  balalnceAccount;
    private List<TransactionSimpleDto> listTransaction; 
    
}
