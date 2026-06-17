package org.golfettozh.finTrack;

import entity.Account;
import entity.User;
import repository.AccountRepository;
import repository.UserRepository;
import service.AccountService;
import service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("fin-track");

    public static void main(String[] args) {

        try (EntityManager em = emf.createEntityManager()) {

            UserRepository userRepository = new UserRepository(em);
            UserService userService = new UserService(userRepository);
            AccountRepository accountRepository = new AccountRepository(em);
            AccountService accountService = new AccountService(accountRepository, userRepository);


            User savedUser = userService.registerUser("Rubens Golfetto", "rubens@gmail.com", "12345678910");
            Account savedAccount = accountService.createAccount(1L,"0001-AB", Account.AccountType.CORRENTE);

            System.out.println("✅ User cadastrado com sucesso! ID: " + savedUser.getId());
            System.out.println("✅ User cadastrado com sucesso! ID: " + savedAccount.getAccountNumber());

        } catch (Exception e) {
            System.err.println("❌ Erro no sistema: " + e.getMessage());
        } finally {
            emf.close();
        }
    }
}