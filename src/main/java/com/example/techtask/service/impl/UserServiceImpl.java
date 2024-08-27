package com.example.techtask.service.impl;

import com.example.techtask.model.User;
import com.example.techtask.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findUser() {
        String query = "SELECT u FROM User u JOIN u.orders o WHERE YEAR(o.createdAt) = 2003 AND o.orderStatus = 'DELIVERED' GROUP BY u.id ORDER BY SUM(o.price * o.quantity) DESC";
        return entityManager.createQuery(query, User.class)
                .setMaxResults(1)
                .getSingleResult();
    }

    @Override
    public List<User> findUsers() {
        String query = "SELECT DISTINCT u FROM User u JOIN u.orders o WHERE YEAR(o.createdAt) = 2010 AND o.orderStatus = 'PAID'";
        return entityManager.createQuery(query, User.class)
                .getResultList();
    }
}