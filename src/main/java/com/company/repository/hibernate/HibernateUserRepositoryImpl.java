package com.company.repository.hibernate;


import com.company.model.User;
import com.company.repository.TeamRepository;
import com.company.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateUserRepositoryImpl implements UserRepository {
    private String getAllMessage = "SELECT * FROM users";

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
        User user = (User) session.get(User.class, id);
        session.close();
        return user;
    }

    public List<User> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<User> list = session.createCriteria(User.class).list();

        /*SQLQuery sqlQuery = session.createSQLQuery(getAllMessage);
        List<User> list = new ArrayList<User>();
        List<Object> result = (List<Object>) sqlQuery.list();
        Iterator itr = result.iterator();
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            Integer identifier = Integer.parseInt(String.valueOf(obj[0]));
            String firstName = String.valueOf(obj[1]);
            String lastName = String.valueOf(obj[2]);
            String specialty = String.valueOf(obj[3]);
            Integer team = Integer.parseInt(String.valueOf(obj[4]));

            User user = new User(identifier, firstName, lastName, specialty, teamRepository.getById(team));
            list.add(user);
        }*/
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

        User user = (User) session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
    }
}
