package acme.oddo.data.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "CustomerEntity")
@Table(name = "T_CUSTOMER")
@Data
@NoArgsConstructor
public class CustomerEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMERID")
    private Integer customerID;
    
    @Column(name = "NAME", nullable = false)
    private String name;
    
    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @Column(name = " CREATE_DATE")
    @CreationTimestamp
    private LocalDateTime createOn;

    @Column(name = "UPDATE_DATE")
    @UpdateTimestamp
    private LocalDateTime updateOn;
}
