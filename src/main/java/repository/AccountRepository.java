package repository;

import entity.Account;
import jakarta.persistence.EntityManager;

public class AccountRepository extends GenericRepository<Account> {

    public AccountRepository(EntityManager entityManager) {
        super(entityManager, Account.class);
    }
}
