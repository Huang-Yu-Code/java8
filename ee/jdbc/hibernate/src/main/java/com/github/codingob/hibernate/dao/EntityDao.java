package com.github.codingob.hibernate.dao;

import com.github.codingob.hibernate.entity.Entity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
public class EntityDao {
    public static void main(String[] args) {
        Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Entity entity = new Entity("hibernate");
        session.save(entity);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
