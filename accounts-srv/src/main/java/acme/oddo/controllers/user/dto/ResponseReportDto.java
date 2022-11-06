package acme.oddo.controllers.user.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseReportDto {

    private Double balance;
    private List<TransactionDto> lDtos;
}
