package com.example.techtask.service.impl;

import com.example.techtask.model.Order;
import com.example.techtask.service.OrderService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order findOrder() {
        String query = "SELECT o FROM Order o WHERE o.quantity > 1 ORDER BY o.createdAt DESC";
        TypedQuery<Order> typedQuery = entityManager.createQuery(query, Order.class);
        typedQuery.setMaxResults(1);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<Order> findOrders() {
        String query = "SELECT o FROM Order o WHERE o.userId IN (SELECT u.id FROM User u WHERE u.userStatus = 'ACTIVE') ORDER BY o.createdAt";
        TypedQuery<Order> typedQuery = entityManager.createQuery(query, Order.class);
        return typedQuery.getResultList();
    }
}