package acme.oddo.controllers.user.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountsUserInfoDto {
    
    private String customerName;
    private String customerSurname;
    private List<AccountInfoDto> listAccountInfo;  
}
