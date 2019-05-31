package com.company.repository.hibernate;


import com.company.model.Skill;
import com.company.repository.SkillRepository;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HibernateSkillRepositoryImpl implements SkillRepository {
    private String getAllMessage = "SELECT * FROM skills";

    public Skill save(Skill skill) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(skill);
        transaction.commit();
        session.close();
        return skill;
    }

    public Skill getById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Skill skill = (Skill) session.get(Skill.class, id);
        session.close();
        return skill;
    }

    public List<Skill> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        SQLQuery sqlQuery = session.createSQLQuery(getAllMessage);
        List<Skill> list = new ArrayList<Skill>();
        List<Object> result = (List<Object>) sqlQuery.list();
        Iterator itr = result.iterator();
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            Integer identifier = Integer.parseInt(String.valueOf(obj[0]));
            String name = String.valueOf(obj[1]);
            Skill skill = new Skill(identifier, name);
            list.add(skill);
        }
        session.close();
        return list;
    }

    public Skill update(Skill skill) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(skill);
        transaction.commit();
        session.close();
        return skill;
    }

    public void delete(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Skill skill = (Skill) session.get(Skill.class, id);
        session.delete(skill);
        transaction.commit();
        session.close();
    }
}
