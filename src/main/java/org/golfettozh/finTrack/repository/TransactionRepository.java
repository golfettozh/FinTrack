package org.golfettozh.finTrack.repository;

import org.golfettozh.finTrack.entity.Transaction;
import jakarta.persistence.EntityManager;


public class TransactionRepository extends GenericRepository<Transaction> {

    public TransactionRepository(EntityManager entityManager) { super(entityManager , Transaction.class); }
}
