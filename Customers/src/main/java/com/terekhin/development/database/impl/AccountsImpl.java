package com.terekhin.development.database.impl;

import com.terekhin.development.Listeners.EMF;
import com.terekhin.development.database.IAccounts;
import com.terekhin.development.database.Repository;
import com.terekhin.development.domain.Account;
import com.terekhin.development.domain.Transaction;
import com.terekhin.development.helpers.NotificationService;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AccountsImpl extends Repository<Account,Long> implements IAccounts {

    @Override
    public List<Account> getAllByCustomerId(long cid) throws NotificationService {
        entityManager = EMF.createEntityManager();
        CriteriaQuery<Account> _criteria = this.getCriteria();
        Root<Account> root = _criteria.from(Account.class);
        _criteria.select(root).where(entityManager.getCriteriaBuilder().equal(root.get("customerId"),cid));
        try {
            return entityManager.createQuery(_criteria).getResultList();
        }
        catch(Exception e)
        {
            throw new NotificationService(e.getMessage());

        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Account> getAccountsWithoutId(long id) throws NotificationService {
        entityManager = EMF.createEntityManager();
        CriteriaQuery<Account> _criteria = this.getCriteria();
        Root<Account> root = _criteria.from(Account.class);
        _criteria.select(root).where(entityManager.getCriteriaBuilder().notEqual(root.get("id"),id));
        try {
            return entityManager.createQuery(_criteria).getResultList();
        }
        catch(Exception e)
        {
            throw new NotificationService(e.getMessage());

        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean transfer(Account fromAccount, Account toAccount, Transaction transactionFrom,Transaction transactionTo) throws NotificationService {
        entityManager = EMF.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(fromAccount);
            entityManager.merge(toAccount);
            entityManager.persist(transactionFrom);
            entityManager.persist(transactionTo);
            entityManager.getTransaction().commit();
            return true;
        }
        catch(Exception e)
        {
                entityManager.getTransaction().rollback();
                throw new NotificationService(e.getMessage());
        }finally {
                entityManager.close();
        }

    }

}
