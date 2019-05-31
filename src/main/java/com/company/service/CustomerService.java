package com.company.service;


import com.company.model.Customer;
import com.company.repository.CustomerRepository;
import com.company.repository.hibernate.HibernateCustomerRepositoryImpl;

import java.util.List;

public class CustomerService {
    CustomerRepository customerRepository;

    public CustomerService() {
        customerRepository = new HibernateCustomerRepositoryImpl();
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getById(int id) {
        return customerRepository.getById(id);
    }

    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    public Customer update(Customer customer) {
        return customerRepository.update(customer);
    }

    public void delete(int id) {
        customerRepository.delete(id);
    }
}
