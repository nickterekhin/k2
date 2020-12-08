package com.terekhin.development.database.impl;

import com.terekhin.development.Listeners.EMF;
import com.terekhin.development.database.IAccounts;
import com.terekhin.development.database.Repository;
import com.terekhin.development.domain.Account;
import com.terekhin.development.helpers.NotificationService;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AccountsImpl extends Repository<Account,Long> implements IAccounts {

    @Override
    public List<Account> getAllByCustomerId(long cid) throws NotificationService {
        entityManager = EMF.createEntityManager();
        CriteriaQuery<Account> _criteria = this.getCriteria();
        Root<Account> root = _criteria.from(_pojo);
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
}
