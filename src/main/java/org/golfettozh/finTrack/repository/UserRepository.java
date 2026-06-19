package org.golfettozh.finTrack.repository;

import org.golfettozh.finTrack.entity.User;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UserRepository extends GenericRepository<User> {

    public UserRepository(EntityManager entityManager) {
        super(entityManager, User.class);
    }

    public List<User> findAllUsers() {
        return entityManager.createQuery(
                        "SELECT u FROM User u", User.class)
                .getResultList();
    }

    public User findUserById(Long id) {
        return entityManager.find(User.class, id);
    }
}
