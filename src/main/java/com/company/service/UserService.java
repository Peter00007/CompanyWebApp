package com.company.service;


import com.company.model.User;
import com.company.repository.UserRepository;
import com.company.repository.hibernate.HibernateUserRepositoryImpl;

import java.util.List;

public class UserService {
    UserRepository userRepository;

    public UserService() {
        userRepository = new HibernateUserRepositoryImpl();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User getById(int id) {
        return userRepository.getById(id);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public User update(User user) {
        return userRepository.update(user);
    }

    public void delete(int id) {
        userRepository.delete(id);
    }
}
