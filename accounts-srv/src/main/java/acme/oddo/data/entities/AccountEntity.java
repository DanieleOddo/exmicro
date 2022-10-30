package acme.oddo.data.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "AccountEntity")
@Table(name = "Account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountID;
    
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "customerID")
    private CustomerEntity customer;

    @Column
    @CreationTimestamp
    private LocalDateTime createOn;

    @Column
    @UpdateTimestamp
    private LocalDateTime updateOn;

}
