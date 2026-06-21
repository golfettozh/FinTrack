package org.golfettozh.finTrack.repository;

import jakarta.persistence.EntityManager;
import org.golfettozh.finTrack.entity.Category;

import java.util.List;

public class CategoryRepository extends GenericRepository<Category> {
    public CategoryRepository(EntityManager entityManager) {
        super(entityManager, Category.class);
    }

    public List<Category> findAllCategories() {
        return entityManager.createQuery(
                        "SELECT c FROM Category c", Category.class)
                .getResultList();
    }
}