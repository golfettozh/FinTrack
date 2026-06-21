package org.golfettozh.finTrack;


import org.golfettozh.finTrack.menu.AppMenu;
import org.golfettozh.finTrack.repository.AccountRepository;
import org.golfettozh.finTrack.repository.CategoryRepository;
import org.golfettozh.finTrack.repository.TransactionRepository;
import org.golfettozh.finTrack.repository.UserRepository;
import org.golfettozh.finTrack.service.AccountService;
import org.golfettozh.finTrack.service.CategoryService;
import org.golfettozh.finTrack.service.TransactionService;
import org.golfettozh.finTrack.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class Main {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("fin-track");

    // Main.java
    public static void main(String[] args) {
        try (EntityManager em = emf.createEntityManager()) {
            Scanner scanner = new Scanner(System.in);

            UserService userService = new UserService(new UserRepository(em));
            AccountService accountService = new AccountService(new AccountRepository(em), new UserRepository(em));
            TransactionService transactionService = new TransactionService(new TransactionRepository(em), new AccountRepository(em));
            CategoryService categoryService = new CategoryService(new CategoryRepository(em));
            clear();
            new AppMenu(scanner, userService, accountService, transactionService, categoryService).start();
        }

    }

    public static void clear() {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }
}