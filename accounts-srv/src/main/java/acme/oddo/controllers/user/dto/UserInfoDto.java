package acme.oddo.controllers.user.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfoDto {

    private String customerName;
    private String customerSurname;
    private Integer accountID;
    private Double balance; 
    private List<TransactionSimpleDto> listTransaction; 
}
