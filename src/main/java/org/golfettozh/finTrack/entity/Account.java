package org.golfettozh.finTrack.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType type;

    @Column(nullable = false)
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public enum AccountType {
        CORRENTE,
        POUPANCA
    }


    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transaction;

    public Account() {}

    public Account(String accountNumber, User user, BigDecimal balance, AccountType type) {
        this.accountNumber = accountNumber;
        this.user = user;
        this.balance = balance;
        this.type = type;

        setBalance(BigDecimal.valueOf(200)); // Teste, remover dps
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public AccountType getType() {
        return type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return String.format("""
            
            ┌─────────────────────────────┐
            │  ID:    %-20d │
            │  Tipo:  %-20s │
            │  Saldo: R$ %-17.2f │
            └─────────────────────────────┘
            """, getId(), getType(), getBalance());
    }
}