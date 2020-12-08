package com.terekhin.development.database.impl;

import com.terekhin.development.Listeners.EMF;
import com.terekhin.development.database.ICrossCourses;
import com.terekhin.development.database.IRepository;
import com.terekhin.development.database.Repository;
import com.terekhin.development.domain.Account;
import com.terekhin.development.domain.CrossCourse;
import com.terekhin.development.helpers.NotificationService;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CrossCoursesImpl extends Repository<CrossCourse,Long> implements ICrossCourses {
    @Override
    public List<CrossCourse> getAllByCurrencyId(long currency_id) throws NotificationService {
        entityManager = EMF.createEntityManager();
        CriteriaQuery<CrossCourse> _criteria = this.getCriteria();
        Root<CrossCourse> root = _criteria.from(CrossCourse.class);
        _criteria.select(root).where(entityManager.getCriteriaBuilder().equal(root.get("fromCurrencyId"),currency_id));
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
