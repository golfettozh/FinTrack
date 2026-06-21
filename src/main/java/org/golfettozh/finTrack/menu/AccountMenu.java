package org.golfettozh.finTrack.menu;

import org.golfettozh.finTrack.entity.Account;
import org.golfettozh.finTrack.service.AccountService;

import java.util.Scanner;

public class AccountMenu {
    private final Scanner scanner;
    private final AccountService accountService;

    public AccountMenu(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    public void start() {
        int option;
        do {
            System.out.println("\n===== CONTAS =====");
            System.out.println("1. Criar conta");
            System.out.println("2. Listar contas do usuário");
            System.out.println("0. Voltar");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> createAccount();
                case 2 -> listAccounts();
            }
        } while (option != 0);
    }

    private void createAccount() {
        System.out.println("ID do usuário: ");
        Long userId = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Número da conta: ");
        String number = scanner.nextLine();
        System.out.println("Tipo (CORRENTE, POUPANCA): ");
        Account.AccountType type = Account.AccountType.valueOf(scanner.next().toUpperCase());

        accountService.createAccountForUsersWithUserID(userId, number, type);
        System.out.println("✅ Conta criada com sucesso!");
    }

    private void listAccounts() {
        System.out.println("ID do usuário: ");
        Long userId = scanner.nextLong();
        System.out.println(accountService.findAllAccountsByUserId(userId));
    }
}