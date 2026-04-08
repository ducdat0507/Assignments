package com.example.project_4.dao;

import java.util.List;

import com.example.project_4.entity.Ticket;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class TicketDao {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence-unit");

    public List<Ticket> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Ticket> query = em.createNamedQuery("SELECT * FROM Tickets t", Ticket.class);
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
