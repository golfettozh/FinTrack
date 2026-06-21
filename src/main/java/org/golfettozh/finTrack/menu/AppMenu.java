package org.golfettozh.finTrack.menu;

import org.golfettozh.finTrack.service.AccountService;
import org.golfettozh.finTrack.service.CategoryService;
import org.golfettozh.finTrack.service.TransactionService;
import org.golfettozh.finTrack.service.UserService;

import java.util.Scanner;

public class AppMenu {
    private final Scanner scanner;
    private final UserMenu userMenu;
    private final AccountMenu accountMenu;
    private final TransactionMenu transactionMenu;
    private final CategoryMenu categoryMenu; // ← adiciona

    public AppMenu(Scanner scanner, UserService userService,
                   AccountService accountService, TransactionService transactionService,
                   CategoryService categoryService) { // ← adiciona parâmetro
        this.scanner = scanner;
        this.userMenu = new UserMenu(scanner, userService);
        this.accountMenu = new AccountMenu(scanner, accountService);
        this.transactionMenu = new TransactionMenu(scanner, transactionService);
        this.categoryMenu = new CategoryMenu(scanner, categoryService); // ← adiciona
    }

    public void start() {
        int option;
        do {
            System.out.println("\n===== FINTRACK =====");
            System.out.println("1. Usuários");
            System.out.println("2. Contas");
            System.out.println("3. Transações");
            System.out.println("4. Categorias");
            System.out.println("0. Sair");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> userMenu.start();
                case 2 -> accountMenu.start();
                case 3 -> transactionMenu.start();
                case 4 -> categoryMenu.start(); // ← adiciona
            }
        } while (option != 0);
    }
}