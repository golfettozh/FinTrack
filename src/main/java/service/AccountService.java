package service;

import entity.Account;
import entity.User;
import repository.AccountRepository;
import repository.UserRepository;
import java.math.BigDecimal;
import java.util.Optional;

public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public Account createAccount(Long userId, String accountNumber, Account.AccountType type) {

        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado com o ID: " + userId);
        }

        User foundUser = userOptional.get();

        Account newAccount = new Account(accountNumber, foundUser, BigDecimal.ZERO, type);

        foundUser.addAccount(newAccount);

        return accountRepository.save(newAccount);
    }
}