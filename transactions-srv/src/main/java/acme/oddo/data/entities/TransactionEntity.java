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
    @Column(name = "transaction_id")
    private Integer transactionID;

    @NonNull
    @Column(name = "accoaunt_id")
    private Integer accountID; 

    @Column(name = "transaction_dt")
    @CreationTimestamp
    private LocalDateTime createOn;
    
    @Column(name = "invoice_imp")
    private BigDecimal invoiceValue;

    @Column(name = "deposit_imp")
    private BigDecimal depositValue; 

    @PrePersist
    @PreUpdate
    public void precisionConvertion() {
        this.invoiceValue.setScale(2, RoundingMode.HALF_UP);
        this.depositValue.setScale(2, RoundingMode.HALF_UP);
    }
}
