package org.golfettozh.finTrack.repository;

import org.golfettozh.finTrack.entity.Account;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AccountRepository extends GenericRepository<Account> {

    public AccountRepository(EntityManager entityManager) {
        super(entityManager, Account.class);
    }

    public List<Account> findAllByUserId(Long userId) {
        return entityManager.createQuery(
                        "SELECT a FROM Account a WHERE a.user.id = :userId", Account.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public List<Account> findAccountsByUserId(Long userId) {
        return entityManager.createQuery(
                        "SELECT a FROM Account a WHERE a.user.id = :userId", Account.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
