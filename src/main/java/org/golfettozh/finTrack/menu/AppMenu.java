package org.golfettozh.finTrack.menu;


import org.golfettozh.finTrack.service.AccountService;
import org.golfettozh.finTrack.service.TransactionService;
import org.golfettozh.finTrack.service.UserService;

import java.util.Scanner;

// AppMenu.java
public class AppMenu {
    private final Scanner scanner;
    private final UserMenu userMenu;
    private final AccountMenu accountMenu;
    private final TransactionMenu transactionMenu;

    public AppMenu(Scanner scanner, UserService userService,
                   AccountService accountService, TransactionService transactionService) {
        this.scanner = scanner;
        this.userMenu = new UserMenu(scanner, userService);
        this.accountMenu = new AccountMenu(scanner, accountService);
        this.transactionMenu = new TransactionMenu(scanner, transactionService);
    }

    public void start() {
        int option;
        do {
            System.out.println("\n===== FINTRACK =====");
            System.out.println("1. Usuários");
            System.out.println("2. Contas");
            System.out.println("3. Transações");
            System.out.println("0. Sair");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> userMenu.start();
                case 2 -> accountMenu.start();
                case 3 -> transactionMenu.start();
            }
        } while (option != 0);
    }
}