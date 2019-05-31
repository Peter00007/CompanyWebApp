package com.company.repository.hibernate;

import com.company.model.Customer;
import com.company.repository.CustomerRepository;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HibernateCustomerRepositoryImpl implements CustomerRepository {
    private String getAllMessage = "SELECT * FROM customers";

    public Customer save(Customer customer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
        return customer;
    }

    public Customer getById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Customer customer = (Customer) session.get(Customer.class, id);
        session.close();
        return customer;
    }

    public List<Customer> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        SQLQuery sqlQuery = session.createSQLQuery(getAllMessage);
        List<Customer> list = new ArrayList<Customer>();
        List<Object> result = (List<Object>) sqlQuery.list();
        Iterator itr = result.iterator();
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            Integer identifier = Integer.parseInt(String.valueOf(obj[0]));
            String name = String.valueOf(obj[1]);
            Customer customer = new Customer(identifier, name);
            list.add(customer);
        }
        session.close();
        return list;
    }

    public Customer update(Customer customer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(customer);
        transaction.commit();
        session.close();
        return customer;
    }

    public void delete(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = (Customer) session.get(Customer.class, id);
        session.delete(customer);
        transaction.commit();
        session.close();
    }
}
