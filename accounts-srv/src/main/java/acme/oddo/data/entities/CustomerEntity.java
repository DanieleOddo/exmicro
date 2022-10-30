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
@Table(name = "Customer")
@Data
@NoArgsConstructor
public class CustomerEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerID;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String surname;

    @Column
    @CreationTimestamp
    private LocalDateTime createOn;

    @Column
    @UpdateTimestamp
    private LocalDateTime updateOn;
}
