package acme.oddo.controllers.user;

import lombok.Data;

@Data
public class UserDTO {
    
    private String name;
    private String surname;
    private Double balance;
    // private List<Transaction> listTransactions;
}
