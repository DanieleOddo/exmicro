package acme.oddo.data.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "AccountEntity")
@Table(name = "T_ACCOUNT")
@Data
@NoArgsConstructor
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNTID")
    private Integer accountID;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMERID")
    private CustomerEntity customer;

    @Column(name = "CREATE_DATE")
    @CreationTimestamp
    private LocalDateTime createOn;

    @Column(name = "UPDATE_DATE")
    @UpdateTimestamp
    private LocalDateTime updateOn;

}
