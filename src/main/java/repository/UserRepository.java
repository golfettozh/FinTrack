package repository;

import entity.User;
import jakarta.persistence.EntityManager;

public class UserRepository extends GenericRepository<User> {

    public UserRepository(EntityManager entityManager) {
        super(entityManager, User.class);
    }
}
