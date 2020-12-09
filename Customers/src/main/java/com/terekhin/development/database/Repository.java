package com.terekhin.development.database;

import com.terekhin.development.Listeners.EMF;
import com.terekhin.development.domain.CrossCourse;
import com.terekhin.development.helpers.NotificationService;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class Repository<T,K extends Serializable> implements IRepository<T,K> {
    protected Class _pojo;
    protected EntityManager entityManager;

    public Repository() {
        _pojo= (Class) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];

    }

    @Override
    public EntityManager getGlobalTransaction() {
        return EMF.createEntityManager();
    }

    @Override
    public void create(T entity) throws NotificationService {
        try {
            entityManager = EMF.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        }
            catch(Exception e)
            {
                entityManager.getTransaction().rollback();
                throw new NotificationService(e.getMessage());
        }finally {
            entityManager.close();
        }
    }

    @Override
    public T getById(K key) throws NotificationService {
        try {
            entityManager = EMF.createEntityManager();
            return (T) entityManager.find(_pojo, key);
        }
        catch(Exception e)
        {
            throw new NotificationService(e.getMessage());

        }finally {
            entityManager.close();
        }
    }

    @Override
    public T getRequired(K key) throws NotificationService {
        try {
            entityManager = EMF.createEntityManager();
            T entity = (T) entityManager.find(_pojo, key);
            if (entity == null) {
                throw new IllegalArgumentException("Entity with id = " + key + " not exist!");
            }
            return entity;
        }
        catch(Exception e)
        {
            throw new NotificationService(e.getMessage());

        }finally {
            entityManager.close();
        }
    }


    @Override
    public void update(T entity) throws NotificationService {
        try {
            entityManager = EMF.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        }
        catch(Exception e)
        {
            entityManager.getTransaction().rollback();
            throw new NotificationService(e.getMessage());

        }finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(T entity) throws NotificationService {
        try {
            entityManager = EMF.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        }
        catch(Exception e)
        {
            entityManager.getTransaction().rollback();
            throw new NotificationService(e.getMessage());

        }finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(K key) throws NotificationService {


        try {
            entityManager = EMF.createEntityManager();
            CriteriaQuery<T> _criteria = this.getCriteria();
            Root<T> root = _criteria.from(_pojo);
            _criteria.select(root).where(entityManager.getCriteriaBuilder().equal(root.get("id"),key));
            T entity = (T) entityManager.createQuery(_criteria).getSingleResult();
            if (entity == null) {
                throw new IllegalArgumentException("Entity with id = " + key + " not exist!");
            }
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        }
        catch(Exception e)
        {
            entityManager.getTransaction().rollback();
            throw new NotificationService(e.getMessage());

        }finally {
            entityManager.close();
        }
    }

    @Override
    public List<T> getAll() throws NotificationService {
        entityManager = EMF.createEntityManager();
        CriteriaQuery<T> _criteria = this.getCriteria();
        Root<T> root = _criteria.from(_pojo);
        _criteria.select(root);
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
    protected CriteriaQuery<T> getCriteria()
    {
        CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
        return cBuilder.createQuery(_pojo);
    }
}
