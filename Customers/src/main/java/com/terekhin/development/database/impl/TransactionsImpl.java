package com.terekhin.development.database.impl;

import com.terekhin.development.Listeners.EMF;
import com.terekhin.development.database.ITransactions;
import com.terekhin.development.database.Repository;
import com.terekhin.development.domain.Account;
import com.terekhin.development.domain.Transaction;
import com.terekhin.development.domain.TransactionType;
import com.terekhin.development.helpers.NotificationService;
import org.hibernate.HibernateException;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TransactionsImpl extends Repository<Transaction,Long> implements ITransactions {
    @Override
    public List<Transaction> getAllByAccountId(long account_id) throws NotificationService {

            entityManager = EMF.createEntityManager();
            CriteriaQuery<Transaction> _criteria = this.getCriteria();
            Root<Transaction> root = _criteria.from(Transaction.class);
            _criteria.select(root).where(entityManager.getCriteriaBuilder().equal(root.get("accountId"),account_id)).orderBy(entityManager.getCriteriaBuilder().desc(root.get("id")));
            try {
                return entityManager.createQuery(_criteria).getResultList();
            }
            catch(HibernateException e)
            {
                throw new NotificationService(e.getMessage());

            } finally {
                entityManager.close();
            }

    }
}
