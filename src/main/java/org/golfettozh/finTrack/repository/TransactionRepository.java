package org.golfettozh.finTrack.repository;

import org.golfettozh.finTrack.entity.Account;
import org.golfettozh.finTrack.entity.Transaction;
import jakarta.persistence.EntityManager;

import java.util.List;


public class TransactionRepository extends GenericRepository<Transaction> {

    public TransactionRepository(EntityManager entityManager) { super(entityManager , Transaction.class); }

    public List<Transaction> findByAccountId(Long accountId) {
        return entityManager.createQuery(
                        "SELECT t FROM Transaction t WHERE t.account.id = :accountId ORDER BY t.transactionDate DESC", Transaction.class)
                .setParameter("accountId", accountId)
                .getResultList();
    }
}
