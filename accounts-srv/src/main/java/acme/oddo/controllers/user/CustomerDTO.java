package acme.oddo.controllers.user;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class CustomerDTO implements Serializable {
    
    private Integer customerID;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    private LocalDate createOn;
    private LocalDate updateOn;
}
