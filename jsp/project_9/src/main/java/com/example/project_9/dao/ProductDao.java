package com.example.project_9.dao;

import java.util.List;

import com.example.project_9.entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class ProductDao {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence-unit");

    public List<Ticket> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Ticket> query = em.createNamedQuery("SELECT * FROM products p", Product.class);
        List<Ticket> result = query.getResultList();
        em.close();
        return result;
    }

    public void save(Ticket ticket) {
        EntityManager em = emf.createEntityManager();
        em.merge(ticket);
        em.close();
    }
}
