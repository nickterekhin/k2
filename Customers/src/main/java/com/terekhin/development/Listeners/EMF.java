package com.terekhin.development.Listeners;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class EMF implements ServletContextListener {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        emf = Persistence.createEntityManagerFactory("TDB");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        emf.close();
    }
    public static EntityManager createEntityManager() {

        if (emf == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }

        return emf.createEntityManager();
    }
}
