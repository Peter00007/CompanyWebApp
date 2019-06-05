package com.company.repository.hibernate;


import com.company.model.User;
import com.company.repository.TeamRepository;
import com.company.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateUserRepositoryImpl implements UserRepository {
    TeamRepository teamRepository;

    public HibernateUserRepositoryImpl() {
        teamRepository = new HibernateTeamRepositoryImpl();
    }

    public User save(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
        return user;
    }

    public User getById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    public List<User> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<User> list = session.createCriteria(User.class).list();
        session.close();
        return list;
    }

    public User update(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(user);
        transaction.commit();
        session.close();
        return user;
    }

    public void delete(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
    }
}
