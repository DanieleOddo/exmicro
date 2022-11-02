package acme.oddo.controllers.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseUserDTO {

    private CustomerDTO customer;
    private ResponseReportDTO responseReportDTO;    
}
