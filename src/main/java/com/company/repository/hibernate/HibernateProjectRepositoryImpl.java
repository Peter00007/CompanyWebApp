package com.company.repository.hibernate;


import com.company.model.Project;
import com.company.repository.CustomerRepository;
import com.company.repository.ProjectRepository;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HibernateProjectRepositoryImpl implements ProjectRepository {
    private String getAllMessage = "SELECT * FROM projects";

    CustomerRepository customerRepository;

    public HibernateProjectRepositoryImpl() {
        customerRepository = new HibernateCustomerRepositoryImpl();
    }

    public Project save(Project project) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(project);
        transaction.commit();
        session.close();
        return project;
    }

    public Project getById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Project project = (Project) session.get(Project.class, id);
        session.close();
        return project;
    }

    public List<Project> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        SQLQuery sqlQuery = session.createSQLQuery(getAllMessage);
        List<Project> list = new ArrayList<Project>();
        List<Object> result = (List<Object>) sqlQuery.list();
        Iterator itr = result.iterator();
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            Integer identifier = Integer.parseInt(String.valueOf(obj[0]));
            String name = String.valueOf(obj[1]);
            Integer budget = Integer.parseInt(String.valueOf(obj[2]));
            Integer customer = Integer.parseInt(String.valueOf(obj[3]));
            Project project = new Project(identifier, name, budget, customerRepository.getById(customer));
            list.add(project);
        }
        session.close();
        return list;
    }

    public Project update(Project project) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(project);
        transaction.commit();
        session.close();
        return project;
    }

    public void delete(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Project project = (Project) session.get(Project.class, id);
        session.delete(project);
        transaction.commit();
        session.close();
    }
}
