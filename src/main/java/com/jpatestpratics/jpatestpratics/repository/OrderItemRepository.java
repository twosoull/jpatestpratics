package com.jpatestpratics.jpatestpratics.repository;

import com.jpatestpratics.jpatestpratics.domain.OrderItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(OrderItem orderItem){
        em.persist(orderItem);
    }
}
