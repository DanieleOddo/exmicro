package acme.oddo.data.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity(name = "TransactionEntity")
@Table(name = "Transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionID;
    @NonNull
    private Integer accountID; 

    @Column
    @CreationTimestamp
    private LocalDateTime createOn; 
    private BigDecimal invoiceValue;
    private BigDecimal depositValue; 


    @PrePersist
    @PreUpdate
    public void invoiceValuetPrecisionConvertion() {
        // convert your bigdecimal scale to 2 here
        this.invoiceValue.setScale(2, RoundingMode.HALF_UP);
    }

    @PrePersist
    @PreUpdate
    public void depositValuetPrecisionConvertion() {
        // convert your bigdecimal scale to 2 here
        this.depositValue.setScale(2, RoundingMode.HALF_UP);
    }

}
