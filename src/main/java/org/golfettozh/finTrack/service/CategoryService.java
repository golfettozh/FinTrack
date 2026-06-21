package org.golfettozh.finTrack.service;

import org.golfettozh.finTrack.entity.Category;
import org.golfettozh.finTrack.repository.CategoryRepository;

import java.util.List;

public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void registerCategory(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Nome da categoria não pode ser vazio");
        }
        categoryRepository.save(new Category(name));
        System.out.println("✅ Categoria criada com sucesso!");
    }

    public void listAllCategories() {
        List<Category> categories = categoryRepository.findAllCategories();

        System.out.println("┌───────┬──────────────────────────┐");
        System.out.println("│  ID   │  Nome                    │");
        System.out.println("├───────┼──────────────────────────┤");
        categories.forEach(System.out::println);
        System.out.println("└───────┴──────────────────────────┘");
    }
}