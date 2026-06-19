package org.golfettozh.finTrack.menu;

import org.golfettozh.finTrack.entity.Transaction;
import org.golfettozh.finTrack.service.TransactionService;

import java.math.BigDecimal;
import java.util.Scanner;

public class TransactionMenu {
    private final TransactionService transactionService;
    private final Scanner scanner;

    public TransactionMenu(Scanner scanner, TransactionService transactionService) {
        this.scanner = scanner;
        this.transactionService = transactionService;
    }

    public void start() {
        int option;
        do {
            System.out.println("\n===== TRANSAÇÕES =====");
            System.out.println("1. Depositar");
            System.out.println("2. Sacar");
            System.out.println("0. Voltar");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> deposit();
                case 2 -> withdrawal();
            }
        } while (option != 0);
    }

    private void deposit() {
        System.out.println("ID da conta: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Valor a depositar: ");
        BigDecimal value = scanner.nextBigDecimal();
        scanner.nextLine();
        System.out.println("Descrição (opcional, Enter para pular): ");
        String description = scanner.nextLine();

        transactionService.registerDeposit(id, value, description, Transaction.TransactionType.DEPOSITO);
        System.out.println("✅ Depósito realizado com sucesso!");
    }

    private void withdrawal() {
        System.out.println("ID da conta: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Valor a sacar: ");
        BigDecimal value = scanner.nextBigDecimal();

        transactionService.registerWithdrawal(id, value);
        System.out.println("✅ Saque realizado com sucesso!");
    }
}