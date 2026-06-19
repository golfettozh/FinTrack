package org.golfettozh.finTrack.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Transaction.TransactionType type;

    public enum TransactionType {
        DEPOSITO,
        SAQUE,
        TRANSFERENCIA
    }

    @Column(nullable = false)
    private BigDecimal value;

    private String description;

    @Column(nullable = false, name = "transaction_date")
    private LocalDateTime transactionDate;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    /*
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    */

    public Transaction(BigDecimal value, String description,  Account account, TransactionType transactionType) {
        this.value = value;
        this.description = description;
        this.account = account;
        this.type = transactionType;

        prePersist();
    }

    public Transaction() {}

    @PrePersist
    public void prePersist() {
        this.transactionDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getType() {return type;}

    public void setType(TransactionType type) {this.type = type;}

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal balance) {
        this.value = balance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "\n======== TITULAR Transaction ========\n" +
                "Conta ID: " + getId() + "\n" +
                "Description:   " + getDescription() + "\n" +
                "Tipo:     " + getType() + "\n" +
                "=======================================";
    }
}
