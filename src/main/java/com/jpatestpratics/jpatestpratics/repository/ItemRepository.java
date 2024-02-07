package com.jpatestpratics.jpatestpratics.repository;

import com.jpatestpratics.jpatestpratics.domain.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Item item){
        em.persist(item);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i",Item.class).getResultList();
    }


    public Item find(Long id) {
        return em.find(Item.class,id);
    }
}
