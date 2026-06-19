package org.golfettozh.finTrack.service;

import org.golfettozh.finTrack.entity.Account;
import org.golfettozh.finTrack.entity.User;
import org.golfettozh.finTrack.repository.AccountRepository;
import org.golfettozh.finTrack.repository.UserRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public void createAccountForUsersWithUserID(Long userId, String accountNumber, Account.AccountType type) {

        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado com o ID: " + userId);
        }

        User foundUser = userOptional.get();

        Account newAccount = new Account(accountNumber, foundUser, BigDecimal.ZERO, type);

        foundUser.addAccount(newAccount);

        accountRepository.save(newAccount);
    }

    public List<Account> findAllAccountsByUserId(Long userId) {
        if (userId == null || userId <= 0 || accountRepository.findById(userId).isEmpty()) {
            throw new IllegalArgumentException("ID de usuário inválido.");
        }

        return accountRepository.findAllByUserId(userId);
    }
}