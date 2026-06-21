package org.golfettozh.finTrack.service;

import org.golfettozh.finTrack.entity.Account;
import org.golfettozh.finTrack.entity.Transaction;
import org.golfettozh.finTrack.repository.AccountRepository;
import org.golfettozh.finTrack.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public void registerDeposit(Long accountId, BigDecimal amount, String description) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ArithmeticException("Valor negativo ou ZERO, sendo passado no valor de depositar");
        }

        if (description.isBlank()) {
            description = "Depositando dinheiro...";
        }

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));

        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setValue(amount);
        transaction.setDescription(description);
        transaction.setType(Transaction.TransactionType.DEPOSITO);
        transaction.setTransactionDate(LocalDateTime.now());

        transactionRepository.save(transaction);
    }

    public void registerWithdrawal(Long accountId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor negativo ou ZERO");
        }

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
        transactionRepository.save(new Transaction(amount, "saque", account ,Transaction.TransactionType.SAQUE));

        System.out.printf("✅ Saque de R$ %.2f realizado com sucesso!%n", amount);
        System.out.printf("💰 Saldo atual: R$ %.2f%n", account.getBalance());
    }

    public void registerTransfer(Long originAccountId, Long destinationAccountId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor negativo ou ZERO");
        }

        Account originAccount = accountRepository.findById(originAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));
        Account destinationAccount = accountRepository.findById(destinationAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));

        if (originAccount.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        originAccount.setBalance(originAccount.getBalance().subtract(amount));
        destinationAccount.setBalance(destinationAccount.getBalance().add(amount));

        accountRepository.save(originAccount);
        accountRepository.save(destinationAccount);

        transactionRepository.save(new Transaction(amount, "Transferência enviada", originAccount, Transaction.TransactionType.TRANSFERENCIA));
        transactionRepository.save(new Transaction(amount, "Transferência recebida", destinationAccount, Transaction.TransactionType.TRANSFERENCIA));

    }

    public BigDecimal getConsolidatedBalance(Long userId) {
        return accountRepository.findAccountsByUserId(userId)
                .stream()
                .map(Account::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Transaction> getAccountStatement(Long accountId) {
        accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));

        return transactionRepository.findByAccountId(accountId);
    }
}
