package com.terekhin.development.database;

import com.terekhin.development.helpers.NotificationService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;

public interface IRepository<T,K extends Serializable> {
    void create(T entity) throws NotificationService;

    T getById(K key) throws NotificationService;

    T getRequired(K key) throws NotificationService;

    void update(T entity) throws NotificationService;

    void delete(T entity) throws NotificationService;

    void delete(K key) throws NotificationService;

    List<T> getAll() throws NotificationService;

    EntityManager getGlobalTransaction();
}
