package org.golfettozh.finTrack.menu;

import org.golfettozh.finTrack.service.UserService;

import java.util.Scanner;

public class UserMenu {
    private final UserService userService;
    private final Scanner scanner;

    public UserMenu(Scanner scanner, UserService userService) {
        this.scanner = scanner;
        this.userService = userService;
    }

    public void start() {
        int option;
        do {
            System.out.println("\n===== USUÁRIOS =====");
            System.out.println("1. Cadastrar usuário");
            System.out.println("2. Listar Todos os usuários");
            System.out.println("3. Listar usuário pelo ID");
            System.out.println("0. Voltar");
            option = scanner.nextInt();
            scanner.nextLine(); // consome o \n

            switch (option) {
                case 1 -> registerUser();
                case 2 -> System.out.println(userService.returnAllUsers());
                case 3 -> {
                    System.out.println("Digite o ID desejado: ");
                    long id = scanner.nextLong();
                    scanner.nextLine(); // consome o \n
                    System.out.println(userService.returnOneUserByID(id));
                }
            }
        } while (option != 0);
    }

    private void registerUser() {
        System.out.println("Nome: ");
        String name = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("CPF: ");
        String cpf = scanner.nextLine();

        userService.registerUser(name, email, cpf);
        System.out.println("✅ Usuário cadastrado com sucesso!");
    }
}