package org.golfettozh.finTrack.menu;

import org.golfettozh.finTrack.service.CategoryService;

import java.util.Scanner;

public class CategoryMenu {
    private final CategoryService categoryService;
    private final Scanner scanner;

    public CategoryMenu(Scanner scanner, CategoryService categoryService) {
        this.scanner = scanner;
        this.categoryService = categoryService;
    }

    public void start() {
        int option;
        do {
            System.out.println("\n===== CATEGORIAS =====");
            System.out.println("1. Criar categoria");
            System.out.println("2. Listar categorias");
            System.out.println("0. Voltar");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> createCategory();
                case 2 -> categoryService.listAllCategories();
            }
        } while (option != 0);
    }

    private void createCategory() {
        System.out.println("Nome da categoria: ");
        String name = scanner.nextLine();
        categoryService.registerCategory(name);
    }
}