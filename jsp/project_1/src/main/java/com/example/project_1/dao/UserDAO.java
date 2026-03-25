package com.example.project_1.dao;

import com.example.project_1.model.User;
import jakarta.persistence.*;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAO {
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("project1PU");

    public static User findByUsername(String username) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.username = :u", User.class);
            q.setParameter("u", username);
            return q.getResultStream().findFirst().orElse(null);
        } finally {
            em.close();
        }
    }

    public static User createUser(String username, String plainPassword) {
        EntityManager em = emf.createEntityManager();
        try {
            // check exists
            if (findByUsername(username) != null) return null;
            String hash = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
            User u = new User(username, hash);
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            return u;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }
}
